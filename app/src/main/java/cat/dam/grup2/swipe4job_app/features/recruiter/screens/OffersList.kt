package cat.dam.grup2.swipe4job_app.features.recruiter.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cat.dam.grup2.swipe4job_app.CustomError
import cat.dam.grup2.swipe4job_app.R
import cat.dam.grup2.swipe4job_app.features.recruiter.components.RecruiterBottomNavigationBar
import cat.dam.grup2.swipe4job_app.features.recruiter.components.BottomNavigationItem
import cat.dam.grup2.swipe4job_app.features.recruiter.components.OffersListView
import cat.dam.grup2.swipe4job_app.features.recruiter.models.ContractTypeOptions
import cat.dam.grup2.swipe4job_app.features.recruiter.models.JobOfferInformation
import cat.dam.grup2.swipe4job_app.features.recruiter.models.JobTypeOptions
import cat.dam.grup2.swipe4job_app.features.recruiter.models.SalaryRange
import cat.dam.grup2.swipe4job_app.features.recruiter.models.WorkingDayTypeOptions
import cat.dam.grup2.swipe4job_app.features.recruiter.state.OfferListViewModel
import cat.dam.grup2.swipe4job_app.features.users.user_api_service.UserApiService
import java.time.LocalDate
import java.time.ZoneId
import java.util.Date


// var offerList = mutableStateListOf<JobOfferInformation>()
var itemToView by mutableStateOf<JobOfferInformation?>(null)
var itemToEdit by mutableStateOf<JobOfferInformation?>(null)

// GENERATING FAKE DATA:
fun generateFakeData(): MutableList<JobOfferInformation> {
    val jobTitles = listOf(
        "Software Engineer",
        "Data Analyst",
        "UX/UI Designer",
        "Product Manager",
        "Marketing Specialist"
    )
    val locations = listOf("New York", "Los Angeles", "London", "Paris", "Berlin")
    val descriptions = listOf(
        "We are looking for a skilled Software Engineer to join our team.",
        "Join our team as a Data Analyst and work on exciting projects.",
        "Are you a talented UX/UI Designer? Join us and make an impact.",
        "Exciting opportunity for a Product Manager to lead our team.",
        "Join our Marketing team and help us grow our brand."
    )
    val responsibilities = listOf(
        "Develop and maintain high-quality software applications.",
        "Analyze data and provide insights to support business decisions.",
        "Design intuitive and visually appealing user interfaces.",
        "Lead product development and prioritize features.",
        "Develop marketing strategies and campaigns."
    )
    val requirements = listOf(
        "Bachelor's degree in Computer Science or related field.",
        "Experience with data analysis tools and programming languages.",
        "Proficiency in UI/UX design tools and principles.",
        "Previous experience in product management.",
        "Strong communication and analytical skills."
    )
    val jobTypes = JobTypeOptions.values().toList()
    val contractTypes = ContractTypeOptions.values().toList()
    val workingDayTypes = WorkingDayTypeOptions.values().toList()
    val salaryRanges = listOf(
        SalaryRange.LowerThan(15_000.0),
        SalaryRange.Between(15_000.0, 20_000.0),
        SalaryRange.Between(20_000.0, 25_000.0),
        SalaryRange.Between(25_000.0, 35_000.0),
        SalaryRange.Between(35_000.0, 45_000.0),
        SalaryRange.Between(45_000.0, 55_000.0),
        SalaryRange.Between(55_000.0, 65_000.0),
        SalaryRange.GreaterThan(65_000.0)
    )

    val offerList = mutableListOf<JobOfferInformation>()
    repeat(5) {
        val jobTitle = jobTitles.random()
        val location = locations.random()
        val description = descriptions.random()
        val responsibilities = responsibilities.random()
        val requirements = requirements.random()
        val jobType = jobTypes.random()
        val contractType = contractTypes.random()
        val workingDayType = workingDayTypes.random()
        val salaryRange = salaryRanges.random()
        val publicationDate = generateRandomDate()

        offerList.add(
            JobOfferInformation(
                jobTitle = jobTitle,
                location = location,
                description = description,
                responsabilities = responsibilities,
                requirements = requirements,
                jobType = jobType,
                contractType = contractType,
                workingDayType = workingDayType,
                skills = listOf(
                    "Skill1",
                    "Skill2",
                    "Skill3"
                ), // Aquí puedes agregar habilidades ficticias
                salaryRange = salaryRange,
                departmentOrganization = "Department",
                publicationDate = publicationDate
            )
        )
    }
    return offerList
}

fun generateRandomDate(): Date {
    val now = LocalDate.now()
    val last180Days = now.minusDays(180)
    val randomDate = last180Days.plusDays((Math.random() * 180).toLong())
    return Date.from(randomDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
}

@Composable
fun OffersList(navController: NavController, userApiService: UserApiService) {
    var selected by remember { mutableStateOf(BottomNavigationItem.OFFERS) }
    val offerListViewModel = OfferListViewModel.instance

    LaunchedEffect(key1 = true) {
        try {
            println(offerListViewModel.offerList)
        } catch (error: CustomError) {
            // Maneixar l'error, per exemple, mostrant un missatge d'error a l'usuari
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate("companyPostOfferPage1")
                },
                contentColor = MaterialTheme.colorScheme.onPrimary,
                containerColor = MaterialTheme.colorScheme.primary
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.add_button_description)
                )
            }
        },
        bottomBar = {
            RecruiterBottomNavigationBar(
                searchClick = { selected = BottomNavigationItem.SEARCH },
                connectionsClick = { selected = BottomNavigationItem.CONNECTIONS },
                offersClick = { selected = BottomNavigationItem.OFFERS },
                notificationsClick = { selected = BottomNavigationItem.NOTIFICATIONS },
                selected = selected,
                navController = navController
            )
        }
    ) { innerPadding ->
        if (offerListViewModel.offerList.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.noJobOffersPosted_text),
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(horizontal = 32.dp)
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                OffersListView(navController, offerListViewModel.offerList)
            }
        }
    }
}
