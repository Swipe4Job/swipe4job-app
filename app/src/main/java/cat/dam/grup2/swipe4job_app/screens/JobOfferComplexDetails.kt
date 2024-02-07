package cat.dam.grup2.swipe4job_app.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.HistoryEdu
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cat.dam.grup2.swipe4job_app.composables.MatchButtons
import cat.dam.grup2.swipe4job_app.ui.theme.AppTheme
import cat.dam.grup2.swipe4job_app.R


data class JobOfferInformation(
    val description: String,
    val responsabilities: String,
    val requirements: String,
    val jobType: JobTypeOptions,
    val contractType: ContractTypeOptions,
    val workingDayType: WorkingDayTypeOptions,
    val skills: List<String>,
    val salaryRange: String,
    val workingHours: String,
    val departmentOrganisation: String
)


enum class JobTypeOptions {
    Onsite,
    Remotely,
    Hybrid
}

enum class ContractTypeOptions {
    Freelance,
    Internship,
    Temporary,
    Indefinite,
    Other
}

enum class WorkingDayTypeOptions {
    FullTime,
    PartTime,
    Flexible
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun JobOfferComplexDetails() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Backend developer",
                        style = MaterialTheme.typography.headlineMedium
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            )
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                JobOfferInformationDisplay(
                    information =
                    JobOfferInformation(
                        description = "Hello how are you",
                        responsabilities = "Hello how are you",
                        requirements = "Hello how are you",
                        jobType = JobTypeOptions.Hybrid,
                        contractType = ContractTypeOptions.Temporary,
                        workingDayType = WorkingDayTypeOptions.FullTime,
                        skills = listOf("Kotlin", "Android Development", "Web Development"),
                        salaryRange = "> 60.000 €",
                        workingHours = "Monday to Thursday from 9am to 17pm. Fridays from 8am to 14pm.",
                        departmentOrganisation = "Hello how are you"
                    )
                )
            }
            Box(
                modifier = Modifier
                    .height(IntrinsicSize.Min)
            ) {
                MatchButtons()
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
fun JobOfferInformationDisplay(information: JobOfferInformation) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        item {
            Section(title = stringResource(id = R.string.jobOffer_description_title), icon = Icons.Default.Description) {
                Text(text = information.description,
                    style = MaterialTheme.typography.bodyMedium)
            }

            Section(title = stringResource(id = R.string.jobOffer_responsabilities_title), icon = Icons.Default.ListAlt) {
                Text(text = information.description,
                    style = MaterialTheme.typography.bodyMedium)
            }

            Section(title = stringResource(id = R.string.jobOffer_requirements_title), icon = Icons.Default.List) {
                Text(text = information.description,
                    style = MaterialTheme.typography.bodyMedium)
            }

            Section(title = stringResource(id = R.string.jobOffer_jobType_title), icon = Icons.Default.HistoryEdu) {
                Text(text = information.description,
                    style = MaterialTheme.typography.bodyMedium)
            }

            Section(title = stringResource(id = R.string.jobOffer_contractType_title), icon = Icons.Default.HistoryEdu) {
                Text(text = information.description,
                    style = MaterialTheme.typography.bodyMedium)
            }

            Section(title = stringResource(id = R.string.jobOffer_workingDayType_title), icon = Icons.Default.HistoryEdu) {
                Text(text = information.description,
                    style = MaterialTheme.typography.bodyMedium)
            }

            Section(title = stringResource(id = R.string.candidate_skills_title), icon = Icons.Default.HistoryEdu) {
                information.skills.forEach {
                    SuggestionChip(onClick = { /*TODO*/ }, label = { Text(it) })
                }
            }

            Section(title = stringResource(id = R.string.jobOffer_salaryRange_title), icon = Icons.Default.HistoryEdu) {
                Text(text = information.description,
                    style = MaterialTheme.typography.bodyMedium)
            }

            Section(title = stringResource(id = R.string.jobOffer_workingDayType_title), icon = Icons.Default.HistoryEdu) {
                Text(text = information.description,
                    style = MaterialTheme.typography.bodyMedium)
            }

            Section(title = stringResource(id = R.string.jobOffer_departmentOrganisation_title), icon = Icons.Default.HistoryEdu) {
                Text(text = information.description,
                    style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CustomJobOfferComplexDetailsPreview() {
    AppTheme {
        JobOfferComplexDetails()
    }
}