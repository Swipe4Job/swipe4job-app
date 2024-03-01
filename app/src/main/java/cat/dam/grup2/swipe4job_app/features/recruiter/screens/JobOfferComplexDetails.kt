package cat.dam.grup2.swipe4job_app.features.recruiter.screens

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
import androidx.compose.material.icons.filled.Euro
import androidx.compose.material.icons.filled.HistoryEdu
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cat.dam.grup2.swipe4job_app.shared.composables.MatchButtons
import cat.dam.grup2.swipe4job_app.shared.ui.theme.AppTheme
import cat.dam.grup2.swipe4job_app.R
import cat.dam.grup2.swipe4job_app.features.candidate.screens.Section
import cat.dam.grup2.swipe4job_app.features.recruiter.modelos.ContractTypeOptions
import cat.dam.grup2.swipe4job_app.features.recruiter.modelos.JobOfferInformation
import cat.dam.grup2.swipe4job_app.features.recruiter.modelos.JobTypeOptions
import cat.dam.grup2.swipe4job_app.features.recruiter.modelos.SalaryRange
import cat.dam.grup2.swipe4job_app.features.recruiter.modelos.WorkingDayTypeOptions
import cat.dam.grup2.swipe4job_app.shared.composables.IconVector
import java.text.SimpleDateFormat


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
                        location = "Somewhere",
                        jobTitle = "asñodfg",
                        description = "Hello how are you",
                        responsabilities = "Hello how are you",
                        requirements = "Hello how are you",
                        jobType = JobTypeOptions.Hybrid,
                        contractType = ContractTypeOptions.Temporary,
                        workingDayType = WorkingDayTypeOptions.FullTime,
                        skills = listOf("Kotlin", "Android Development", "Web Development"),
                        salaryRange = SalaryRange.Between(45_000.0, 55_000.0),
                        workingHours = "Monday to Thursday from 9am to 17pm. Fridays from 8am to 14pm.",
                        departmentOrganisation = "Hello how are you",
                        publicationDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2023-01-15 12:30:00")
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
@OptIn(ExperimentalMaterial3Api::class)
fun JobOfferInformationDisplay(information: JobOfferInformation) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        item {
            Section(title = stringResource(id = R.string.jobOffer_description_title), icon = IconVector.ImageVectorIcon(Icons.Default.Description)) {
                Text(text = information.description,
                    style = MaterialTheme.typography.bodyMedium)
            }

            Section(title = stringResource(id = R.string.jobOffer_responsabilities_title), icon = IconVector.ImageVectorIcon(Icons.Default.ListAlt)) {
                Text(text = information.responsabilities,
                    style = MaterialTheme.typography.bodyMedium)
            }

            Section(title = stringResource(id = R.string.jobOffer_requirements_title), icon = IconVector.ImageVectorIcon(Icons.Default.List)) {
                Text(text = information.requirements,
                    style = MaterialTheme.typography.bodyMedium)
            }

            Section(title = stringResource(id = R.string.jobOffer_jobType_title), icon = IconVector.PainterIcon(painterResource(id = R.drawable.explore_nearby_icon))) {
                val jobType = when(information.jobType) {
                    JobTypeOptions.Onsite -> stringResource(id = R.string.jobTypeOption_onSite_text)
                    JobTypeOptions.Remotely -> stringResource(id = R.string.jobTypeOption_remotely_text)
                    JobTypeOptions.Hybrid -> stringResource(id = R.string.jobTypeOption_hybrid_text)
                }

                Text(text = jobType,
                    style = MaterialTheme.typography.bodyMedium)
            }

            Section(title = stringResource(id = R.string.jobOffer_contractType_title), icon = IconVector.PainterIcon(painterResource(id = R.drawable.contract_icon))) {
                val contractType = when(information.contractType) {
                    ContractTypeOptions.Freelance -> stringResource(id = R.string.contractTypeOptions_freelance_text)
                    ContractTypeOptions.Internship -> stringResource(id = R.string.contractTypeOptions_internship_text)
                    ContractTypeOptions.Temporary -> stringResource(id = R.string.contractTypeOptions_temporary_text)
                    ContractTypeOptions.Indefinite -> stringResource(id = R.string.contractTypeOptions_indefinite_text)
                    ContractTypeOptions.Other -> stringResource(id = R.string.contractTypeOptions_other_text)
                }

                Text(text = contractType,
                    style = MaterialTheme.typography.bodyMedium)
            }

            Section(title = stringResource(id = R.string.jobOffer_workingDayType_title), icon = IconVector.PainterIcon(painterResource(id = R.drawable.calendar_clock_icon))) {
                val workingDayType = when(information.workingDayType) {
                    WorkingDayTypeOptions.FullTime -> stringResource(id = R.string.workingDayType_fullTime_text)
                    WorkingDayTypeOptions.PartTime -> stringResource(id = R.string.workingDayType_partTime_text)
                    WorkingDayTypeOptions.Flexible -> stringResource(id = R.string.workingDayType_flexible_text)
                }

                Text(text = workingDayType,
                    style = MaterialTheme.typography.bodyMedium)
            }

            Section(title = stringResource(id = R.string.candidate_skills_title), icon = IconVector.ImageVectorIcon(Icons.Default.HistoryEdu)) {
                information.skills.forEach {
                    SuggestionChip(onClick = { /*TODO*/ }, label = { Text(it) })
                }
            }

            Section(title = stringResource(id = R.string.jobOffer_salaryRange_title), icon = IconVector.ImageVectorIcon(Icons.Default.Euro)) {
                val salaryString = when(information.salaryRange) {
                    is SalaryRange.GreaterThan -> "> ${information.salaryRange.salary} €"
                    is SalaryRange.Between -> "${information.salaryRange.start} € - ${information.salaryRange.end} €"
                    is SalaryRange.LowerThan -> "< ${information.salaryRange.salary} €"
                }
                Text(text = salaryString,
                    style = MaterialTheme.typography.bodyMedium)
            }

            Section(title = stringResource(id = R.string.jobOffer_workingHours_title), icon = IconVector.ImageVectorIcon(Icons.Default.Schedule)) {
                information.workingHours?.let {
                    Text(text = it,
                        style = MaterialTheme.typography.bodyMedium)
                }
            }

            Section(title = stringResource(id = R.string.jobOffer_departmentOrganisation_title), icon = IconVector.ImageVectorIcon(Icons.Default.Hub)) {
                Text(text = information.departmentOrganisation,
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