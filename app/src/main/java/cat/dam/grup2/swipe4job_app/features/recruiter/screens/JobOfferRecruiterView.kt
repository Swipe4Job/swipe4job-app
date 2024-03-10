package cat.dam.grup2.swipe4job_app.features.recruiter.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Euro
import androidx.compose.material.icons.filled.HistoryEdu
import androidx.compose.material.icons.filled.Hub
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cat.dam.grup2.swipe4job_app.R
import cat.dam.grup2.swipe4job_app.features.candidate.screens.Section
import cat.dam.grup2.swipe4job_app.features.recruiter.models.ContractTypeOptions
import cat.dam.grup2.swipe4job_app.features.recruiter.models.JobOfferInformation
import cat.dam.grup2.swipe4job_app.features.recruiter.models.JobTypeOptions
import cat.dam.grup2.swipe4job_app.features.recruiter.models.SalaryRange
import cat.dam.grup2.swipe4job_app.features.recruiter.models.WorkingDayTypeOptions
import cat.dam.grup2.swipe4job_app.shared.composables.IconVector
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun JobOfferRecruiterView(navController: NavController, selectedItem: JobOfferInformation) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(id = R.string.back_icon_description),
                        )
                    }
                }
            )
        }
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
                JobOfferInformationDisplayRecruiterView(selectedItem)
            }
        }
    }
    // Manejar el caso cuando no se encuentra el elemento seleccionado
}


@Composable
fun JobOfferInformationDisplayRecruiterView(information: JobOfferInformation) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        item {
            Section(
                title = stringResource(id = R.string.jobOffer_jobTitle_title),
                icon = IconVector.ImageVectorIcon(
                    Icons.Default.Person
                )
            ) {
                Text(
                    text = information.jobTitle,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Section(
                title = stringResource(id = R.string.jobOffer_location_title),
                icon = IconVector.ImageVectorIcon(
                    Icons.Default.LocationOn
                )
            ) {
                Text(
                    text = information.location,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Section(
                title = stringResource(id = R.string.jobOffer_description_title),
                icon = IconVector.ImageVectorIcon(
                    Icons.Default.Description
                )
            ) {
                Text(
                    text = information.description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Section(
                title = stringResource(id = R.string.jobOffer_responsabilities_title),
                icon = IconVector.ImageVectorIcon(
                    Icons.Default.ListAlt
                )
            ) {
                Text(
                    text = information.responsabilities,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Section(
                title = stringResource(id = R.string.jobOffer_requirements_title),
                icon = IconVector.ImageVectorIcon(
                    Icons.Default.List
                )
            ) {
                Text(
                    text = information.requirements,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Section(
                title = stringResource(id = R.string.jobOffer_jobType_title),
                icon = IconVector.PainterIcon(
                    painterResource(id = R.drawable.explore_nearby_icon)
                )
            ) {
                val jobType = when (information.jobType) {
                    JobTypeOptions.Onsite -> stringResource(id = R.string.jobTypeOption_onSite_text)
                    JobTypeOptions.Remotely -> stringResource(id = R.string.jobTypeOption_remotely_text)
                    JobTypeOptions.Hybrid -> stringResource(id = R.string.jobTypeOption_hybrid_text)
                }

                Text(
                    text = jobType,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Section(
                title = stringResource(id = R.string.jobOffer_contractType_title),
                icon = IconVector.PainterIcon(
                    painterResource(id = R.drawable.contract_icon)
                )
            ) {
                val contractType = when (information.contractType) {
                    ContractTypeOptions.Freelance -> stringResource(id = R.string.contractTypeOptions_freelance_text)
                    ContractTypeOptions.Internship -> stringResource(id = R.string.contractTypeOptions_internship_text)
                    ContractTypeOptions.Temporary -> stringResource(id = R.string.contractTypeOptions_temporary_text)
                    ContractTypeOptions.Indefinite -> stringResource(id = R.string.contractTypeOptions_indefinite_text)
                    ContractTypeOptions.Other -> stringResource(id = R.string.contractTypeOptions_other_text)
                }

                Text(
                    text = contractType,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Section(
                title = stringResource(id = R.string.jobOffer_workingDayType_title),
                icon = IconVector.PainterIcon(
                    painterResource(id = R.drawable.calendar_clock_icon)
                )
            ) {
                val workingDayType = when (information.workingDayType) {
                    WorkingDayTypeOptions.FullTime -> stringResource(id = R.string.workingDayType_fullTime_text)
                    WorkingDayTypeOptions.PartTime -> stringResource(id = R.string.workingDayType_partTime_text)
                    WorkingDayTypeOptions.Flexible -> stringResource(id = R.string.workingDayType_flexible_text)
                }

                Text(
                    text = workingDayType,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Section(
                title = stringResource(id = R.string.candidate_softskills_title),
                icon = IconVector.ImageVectorIcon(
                    Icons.Default.HistoryEdu
                )
            ) {
                information.skills.forEach {
                    SuggestionChip(onClick = { /*TODO*/ }, label = { Text(it) })
                }
            }

            Section(
                title = stringResource(id = R.string.jobOffer_salaryRange_title),
                icon = IconVector.ImageVectorIcon(
                    Icons.Default.Euro
                )
            ) {
                val salaryString = when (information.salaryRange) {
                    is SalaryRange.GreaterThan -> "> ${information.salaryRange.salary} €"
                    is SalaryRange.Between -> "${information.salaryRange.start} € - ${information.salaryRange.end} €"
                    is SalaryRange.LowerThan -> "< ${information.salaryRange.salary} €"
                }
                Text(
                    text = salaryString,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Section(
                title = stringResource(id = R.string.jobOffer_workingHours_title),
                icon = IconVector.ImageVectorIcon(
                    Icons.Default.Schedule
                )
            ) {
                information.workingHours?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            Section(
                title = stringResource(id = R.string.jobOffer_departmentOrganisation_title),
                icon = IconVector.ImageVectorIcon(
                    Icons.Default.Hub
                )
            ) {
                Text(
                    text = information.departmentOrganisation,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val formattedDate = dateFormatter.format(information.publicationDate)
            Section(
                title = stringResource(id = R.string.jobOffer_publicationDate_title),
                icon = IconVector.ImageVectorIcon(
                    Icons.Default.LocationOn
                )
            ) {
                Text(
                    text = formattedDate,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}