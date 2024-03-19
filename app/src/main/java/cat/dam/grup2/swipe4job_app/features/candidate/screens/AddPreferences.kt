package cat.dam.grup2.swipe4job_app.features.candidate.screens

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import cat.dam.grup2.swipe4job_app.CustomError
import cat.dam.grup2.swipe4job_app.R
import cat.dam.grup2.swipe4job_app.features.candidate.model.CandidatePreferences
import cat.dam.grup2.swipe4job_app.features.candidate.state.CandidateProfileViewModel
import cat.dam.grup2.swipe4job_app.features.recruiter.models.ContractTypeOptions
import cat.dam.grup2.swipe4job_app.features.recruiter.models.JobTypeOptions
import cat.dam.grup2.swipe4job_app.features.recruiter.models.SalaryRange
import cat.dam.grup2.swipe4job_app.features.recruiter.models.WorkingDayTypeOptions
import cat.dam.grup2.swipe4job_app.shared.composables.CustomDropdown

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPreferences(navController: NavController) {

    // create candidate prefences object
//    CandidateProfileViewModel.getInstance().preferences = createdPreferencesObject

//    lateinit var preferences: CandidatePreferences
    var selectedSalaryRange by remember { mutableStateOf<SalaryRange?>(null) }
    var selectedJobType by remember { mutableStateOf<JobTypeOptions?>(null) }
    var selectedWorkingDayType by remember { mutableStateOf<WorkingDayTypeOptions?>(null) }
    var selectedContractType by remember { mutableStateOf<ContractTypeOptions?>(null) }
    var candidateProfileViewModel = CandidateProfileViewModel.getInstance()
    var preferences = candidateProfileViewModel.preferences

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        IconButton(
                            onClick = {
                                navController.popBackStack()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = stringResource(id = R.string.back_icon_description),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                        Text(
                            text = stringResource(id = R.string.addPreferences_text),
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .weight(1f)
                        )
                        Text(
                            /* TODO: verify that all the fields are not empty */
                            text = stringResource(id = R.string.save_text),
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier
                                .clickable {
                                    preferences.value = CandidatePreferences(
                                        selectedSalaryRange!!,
                                        selectedWorkingDayType!!,
                                        selectedJobType!!,
                                        selectedContractType!!
                                    )
                                    /* TODO: Save data in database*/
                                    navController.popBackStack()
                                }
                                .padding(end = 16.dp),
                            color = MaterialTheme.colorScheme.primary
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
                AddPreferencesContent(
                    onSalaryRangeChange = { selectedSalaryRange = it },
                    onJobTypeChange = { selectedJobType = it },
                    onWorkingDayTypeChange = { selectedWorkingDayType = it },
                    onContractTypeChange = { selectedContractType = it }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddPreferencesContent(
    onSalaryRangeChange: (SalaryRange) -> Unit,
    onJobTypeChange: (JobTypeOptions) -> Unit,
    onWorkingDayTypeChange: (WorkingDayTypeOptions) -> Unit,
    onContractTypeChange: (ContractTypeOptions) -> Unit
) {
    var salaryRangeText = stringResource(id = R.string.salaryRange_text)
    var selectedSalaryRangeItem by remember { mutableStateOf(salaryRangeText) }
    var salaryRangeOptions = stringArrayResource(id = R.array.salary_range_array).toList()

    var jobTypeText = stringResource(id = R.string.jobType_text)
    var selectedJobTypeItem by remember { mutableStateOf(jobTypeText) }
    var jobTypeOptions = stringArrayResource(id = R.array.job_type_array).toList()

    var workingDayTypeText = stringResource(id = R.string.workingDayType_text)
    var selectedWorkingDayTypeItem by remember { mutableStateOf(workingDayTypeText) }
    var workingDayTypeOptions = stringArrayResource(id = R.array.working_day_type_array).toList()

    var contractTypeText = stringResource(id = R.string.contractType_text)
    var selectedContractTypeItem by remember { mutableStateOf(contractTypeText) }
    var contractTypeOptions = stringArrayResource(id = R.array.contract_type_array).toList()

    val context = LocalContext.current

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            CustomDropdown(
                placeholder = selectedSalaryRangeItem,
                items = salaryRangeOptions
            ) {
                onSalaryRangeChange(toSalaryRange(context, text = it))
            }

            Spacer(modifier = Modifier.height(16.dp))

            CustomDropdown(
                placeholder = selectedJobTypeItem,
                items = jobTypeOptions
            ) {
                onJobTypeChange(toJobType(context, text = it))
            }

            Spacer(modifier = Modifier.height(16.dp))

            CustomDropdown(
                placeholder = selectedWorkingDayTypeItem,
                items = workingDayTypeOptions
            ) {
                onWorkingDayTypeChange(toWorkingDayType(context, it))
            }

            Spacer(modifier = Modifier.height(16.dp))

            CustomDropdown(
                placeholder = selectedContractTypeItem,
                items = contractTypeOptions
            ) {
                onContractTypeChange(toContractType(context, it))
            }
        }
    }
}

fun toJobType(context: Context, text: String): JobTypeOptions {
    return when (text) {
        context.resources.getStringArray(R.array.job_type_array)
            .toList()[0] -> JobTypeOptions.Remotely

        context.resources.getStringArray(R.array.job_type_array)
            .toList()[1] -> JobTypeOptions.Onsite

        context.resources.getStringArray(R.array.job_type_array)
            .toList()[2] -> JobTypeOptions.Hybrid

        else -> {
            throw CustomError("Can not convert $text to a job type")
        }
    }
}

fun toSalaryRange(context: Context, text: String): SalaryRange {
    return when (text) {
        context.resources.getStringArray(R.array.salary_range_array)
            .toList()[0] -> SalaryRange.LowerThan(15_000.0)

        context.resources.getStringArray(R.array.salary_range_array)
            .toList()[1] -> SalaryRange.Between(15_000.0, 20_000.0)

        context.resources.getStringArray(R.array.salary_range_array)
            .toList()[2] -> SalaryRange.Between(20_000.0, 25_000.0)

        context.resources.getStringArray(R.array.salary_range_array)
            .toList()[3] -> SalaryRange.Between(25_000.0, 35_000.0)

        context.resources.getStringArray(R.array.salary_range_array)
            .toList()[4] -> SalaryRange.Between(35_000.0, 45_000.0)

        context.resources.getStringArray(R.array.salary_range_array)
            .toList()[5] -> SalaryRange.Between(45_000.0, 55_000.0)

        context.resources.getStringArray(R.array.salary_range_array)
            .toList()[6] -> SalaryRange.Between(55_000.0, 65_000.0)

        context.resources.getStringArray(R.array.salary_range_array)
            .toList()[7] -> SalaryRange.GreaterThan(65_000.0)

        else -> {
            throw CustomError("Can not convert $text to a contract type")
        }
    }
}

fun toWorkingDayType(context: Context, text: String): WorkingDayTypeOptions {
    return when (text) {
        context.resources.getStringArray(R.array.working_day_type_array)
            .toList()[0] -> WorkingDayTypeOptions.FullTime

        context.resources.getStringArray(R.array.working_day_type_array)
            .toList()[1] -> WorkingDayTypeOptions.PartTime

        context.resources.getStringArray(R.array.working_day_type_array)
            .toList()[2] -> WorkingDayTypeOptions.Flexible

        else -> {
            throw CustomError("Can not convert $text to a working day type")
        }
    }
}

fun toContractType(context: Context, text: String): ContractTypeOptions {
    val stringResourceList =
        context.resources.getStringArray(R.array.contract_type_array)
        .toList()
    return when (text) {
        stringResourceList[0] -> ContractTypeOptions.Indefinite

        stringResourceList[1] -> ContractTypeOptions.Temporary

        stringResourceList[2] -> ContractTypeOptions.Freelance

        stringResourceList[3] -> ContractTypeOptions.Internship

        stringResourceList[4] -> ContractTypeOptions.Other

        else -> {
            throw CustomError("Can not convert $text to a contract type")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddPreferencesPreview() {
    AddPreferences(rememberNavController())
}