package cat.dam.grup2.swipe4job_app.features.recruiter.screens

import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import cat.dam.grup2.swipe4job_app.CustomError
import cat.dam.grup2.swipe4job_app.R
import cat.dam.grup2.swipe4job_app.features.recruiter.models.ContractTypesList
import cat.dam.grup2.swipe4job_app.features.recruiter.models.JobTypesList
import cat.dam.grup2.swipe4job_app.features.recruiter.models.OfferData
import cat.dam.grup2.swipe4job_app.features.recruiter.models.WorkingDayTypesList
import cat.dam.grup2.swipe4job_app.features.recruiter.state.OfferViewModel
import cat.dam.grup2.swipe4job_app.features.users.state.UserViewModel
import cat.dam.grup2.swipe4job_app.features.users.user_api_service.model.UserData
import cat.dam.grup2.swipe4job_app.shared.composables.CustomButton
import cat.dam.grup2.swipe4job_app.shared.composables.CustomDropdown
import cat.dam.grup2.swipe4job_app.shared.composables.CustomOutlinedTextField
import cat.dam.grup2.swipe4job_app.shared.composables.LabelledSwitch
import cat.dam.grup2.swipe4job_app.ui.theme.AppTheme


@Composable
fun CompanyPostOfferPage1(navController: NavController) {
    var jobTitle by remember { mutableStateOf("") }
    var companyName by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }

    val switchIsOn = remember { mutableStateOf(false) }

    var jobTypeText = stringResource(id = R.string.label_jobType)
    var contractTypeText = stringResource(id = R.string.label_contractType)
    var workingDayTypeText = stringResource(id = R.string.label_workingDayType)

    var selectedJobTypeItem by remember { mutableStateOf(jobTypeText) }
    var selectedContractTypeItem by remember { mutableStateOf(contractTypeText) }
    var selectedWorkingDayTypeItem by remember { mutableStateOf(workingDayTypeText) }

    var jobTypeOptions = stringArrayResource(R.array.job_type_array).toList()
    var contractTypeOptions = stringArrayResource(R.array.contract_type_array).toList()
    var workingDayTypeOptions = stringArrayResource(R.array.working_day_type_array).toList()

    // Aquesta és la llista de recursos de jobType
    val jobTypeResourceList = stringArrayResource(id = R.array.job_type_array).toList()
    val contractTypeResourceList = stringArrayResource(id = R.array.contract_type_array).toList()
    val workingDayTypeResourceList = stringArrayResource(id = R.array.working_day_type_array).toList()

    // Variable per desar l'ítem seleccionat del dropdown
    var selectedJobTypeItemList by remember { mutableStateOf(jobTypeResourceList[0]) }
    var selectedContractTypeItemList by remember { mutableStateOf(contractTypeResourceList[0]) }
    var selectedWorkingDayTypeItemList by remember { mutableStateOf(workingDayTypeResourceList[0]) }

    var selectedJobTypeIndex = jobTypeResourceList.indexOf(selectedJobTypeItemList)
    var selectedContractTypeIndex = contractTypeResourceList.indexOf(selectedContractTypeItemList)
    var selectedWorkingDayTypeIndex = workingDayTypeResourceList.indexOf(selectedWorkingDayTypeItemList)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background)
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {

                    // Steps number
                    Text(
                        "1/3",
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .background(Color.Transparent)
                            .align(Alignment.End)
                    )

                    // Spacer
                    Spacer(modifier = Modifier.height(16.dp))

                    // Section title
                    Text(
                        stringResource(id = R.string.postJobOffer_text),
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    )

                    // Title - Job title
                    Text(
                        stringResource(id = R.string.jobTitle_text),
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.titleMedium
                    )

                    // Job title TextField
                    CustomOutlinedTextField(
                        value = jobTitle,
                        onValueChange = { jobTitle = it },
                        label = stringResource(id = R.string.label_jobTitle),
                        leadingIcon = null,
                        iconContentDescription = null,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Text
                        )
                    )

                    // Spacer
                    Spacer(modifier = Modifier.height(16.dp))

                    // Title - Company
                    Text(
                        stringResource(id = R.string.companyName_text),
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.titleMedium
                    )

                    // Company Checkbox

                    CustomOutlinedTextField(
                        value = companyName,
                        onValueChange = { companyName = it },
                        label = stringResource(id = R.string.label_companyName),
                        leadingIcon = null,
                        iconContentDescription = null,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Text
                        )
                    )

                    // TODO: it functionality
//                    LabelledSwitch(
//                        checked = switchIsOn.value,
//                        label = stringResource(id = R.string.hideCompanyName_text),
//                        onCheckedChange = { switchIsOn.value = it },
//                        colors = SwitchDefaults.colors(
//                            checkedTrackColor = MaterialTheme.colorScheme.primaryContainer,
//                            checkedThumbColor = MaterialTheme.colorScheme.primary,
//                            uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
//                            uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer
//                        )
//                    )

                    // Spacer
                    Spacer(modifier = Modifier.height(16.dp))

                    // Title - Job type
                    Text(
                        stringResource(id = R.string.jobType_text),
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.titleMedium
                    )

                    // Job type dropdown
                    CustomDropdown(
                        placeholder = selectedJobTypeItem,
                        items = jobTypeOptions
                    ) { selectedItem ->
                        selectedJobTypeItem = selectedItem
                        selectedJobTypeIndex = jobTypeResourceList.indexOf(selectedItem)
                    }


                    // Spacer
                    Spacer(modifier = Modifier.height(16.dp))

                    // Title - Location
                    Text(
                        stringResource(id = R.string.location_text),
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.titleMedium
                    )

                    // Location TextField
                    CustomOutlinedTextField(
                        value = location,
                        onValueChange = { location = it },
                        label = stringResource(id = R.string.label_location),
                        leadingIcon = null,
                        iconContentDescription = null,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Text
                        )
                    )

                    // Spacer
                    Spacer(modifier = Modifier.height(16.dp))

                    // Title - Contract type
                    Text(
                        stringResource(id = R.string.contractType_text),
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.titleMedium
                    )

                    // Contract type dropdown
                    CustomDropdown(
                        placeholder = selectedContractTypeItem,
                        items = contractTypeOptions
                    ) { selectedItem ->
                        selectedContractTypeItem = selectedItem
                        selectedContractTypeIndex = contractTypeResourceList.indexOf(selectedItem)
                    }

                    // Spacer
                    Spacer(modifier = Modifier.height(16.dp))

                    // Title - Working day type
                    Text(
                        stringResource(id = R.string.workingDayType_text),
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.titleMedium
                    )

                    // Working day type dropdown
                    CustomDropdown(
                        placeholder = selectedWorkingDayTypeItem,
                        items = workingDayTypeOptions
                    ) { selectedItem ->
                        selectedWorkingDayTypeItem = selectedItem
                        selectedWorkingDayTypeIndex = workingDayTypeResourceList.indexOf(selectedItem)
                    }

                    // Spacer
                    Spacer(modifier = Modifier.height(16.dp))

                    val screenWidth = LocalConfiguration.current.screenWidthDp.dp
                    // Buttons - Next
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            CustomButton(
                                onClick = {
                                    navController.navigate("offersList")
                                },
                                text = stringResource(id = R.string.button_cancel_text),
                                modifier = Modifier.weight(1f)
                            )

                            CustomButton(
                                onClick = {
//                                    println("Índex seleccionat: " + JobTypesList.jobTypes[selectedJobTypeIndex])
//                                    println("Índex seleccionat: " + ContractTypesList.contractTypes[selectedContractTypeIndex])
//                                    println("Índex seleccionat: " + WorkingDayTypesList.workingDayTypes[selectedWorkingDayTypeIndex])
                                    val offerViewModel = OfferViewModel.instance

                                    offerViewModel.title = jobTitle
                                    offerViewModel.recruiterId = "d75267d3-9e04-48a9-a34a-e2e84d7f83bc"
                                    offerViewModel.companyName = companyName
                                    offerViewModel.location = location
                                    offerViewModel.jobType = JobTypesList.jobTypes[selectedJobTypeIndex]
                                    offerViewModel.contractType = ContractTypesList.contractTypes[selectedContractTypeIndex]
                                    offerViewModel.workingDay = WorkingDayTypesList.workingDayTypes[selectedWorkingDayTypeIndex]

                                    navController.navigate("companyPostOfferPage2")
                                },
                                text = stringResource(id = R.string.button_next_text),
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CustomCompanyPostOfferPage1Preview() {
    AppTheme {
        CompanyPostOfferPage1(rememberNavController())
    }
}