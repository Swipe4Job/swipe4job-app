package cat.dam.grup2.swipe4job_app.features.recruiter.screens

import android.annotation.SuppressLint
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cat.dam.grup2.swipe4job_app.R
import cat.dam.grup2.swipe4job_app.features.recruiter.models.OfferPost
import cat.dam.grup2.swipe4job_app.features.recruiter.models.SalaryRangeList
import cat.dam.grup2.swipe4job_app.features.recruiter.state.OfferViewModel
import cat.dam.grup2.swipe4job_app.features.users.user_api_service.UserApiService
import cat.dam.grup2.swipe4job_app.shared.composables.CustomButton
import cat.dam.grup2.swipe4job_app.shared.composables.CustomDropdown
import cat.dam.grup2.swipe4job_app.shared.composables.CustomTextFieldMaxChar
import kotlinx.coroutines.launch

@SuppressLint("UnrememberedMutableState")
@Composable
fun CompanyPostOfferPage3(navController: NavController, userApiService: UserApiService) {
    val context = LocalContext.current
    var salaryRangeText = stringResource(id = R.string.label_salaryRange)
    var selectedSalaryRangeItem by remember {
        if (OfferViewModel.instance.salaryRange == "") {
            mutableStateOf(salaryRangeText)
        } else {
            mutableStateOf(
                SalaryRangeList.toResourceString(
                    context,
                    OfferViewModel.instance.salaryRange
                )
            )
        }
    }
    var salaryRangeResourceList = stringArrayResource(R.array.salary_range_array).toList()

    // Variable per desar l'ítem seleccionat del dropdown
    val scope = rememberCoroutineScope()

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
                        "3/3",
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .background(Color.Transparent)
                            .align(Alignment.End)
                    )

                    // Spacer
                    Spacer(modifier = Modifier.height(16.dp))

                    // Title - Salary range
                    Text(
                        stringResource(id = R.string.salaryRange_text),
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.titleMedium
                    )

                    CustomDropdown(
                        placeholder = selectedSalaryRangeItem,
                        items = salaryRangeResourceList
                    ) { selectedItem ->
                        selectedSalaryRangeItem = selectedItem
                        OfferViewModel.instance.salaryRange =
                            SalaryRangeList.fromResourceString(context, selectedItem)
                    }


                    Text(
                        stringResource(id = R.string.grossAnnualSalary_text),
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    // Spacer
                    Spacer(modifier = Modifier.height(16.dp))

                    // Title - Working hours
                    Text(
                        stringResource(id = R.string.workingHours_text),
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.titleMedium
                    )

                    // Text field for the working hours
                    var workingHours = remember {
                        var value = OfferViewModel.instance.workingHours
                        mutableStateOf(value)
                    }

                    LaunchedEffect(workingHours.value) {
                        val offerViewModel = OfferViewModel.instance
                        offerViewModel.workingHours = workingHours.value
                    }

                    CustomTextFieldMaxChar(
                        descriptionState = workingHours,
                        maxCharacters = 500,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Text
                        )
                    )

                    // Spacer
                    Spacer(modifier = Modifier.height(16.dp))

                    // Title - Department organization and relationships
                    Text(
                        stringResource(id = R.string.departmentOrganisation_text),
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.titleMedium
                    )

                    // Text field for the department organization and relationships
                    var departmentOrganisation = remember {
                        var value = OfferViewModel.instance.departmentOrganisation
                        mutableStateOf(value)
                    }

                    LaunchedEffect(departmentOrganisation.value) {
                        val offerViewModel = OfferViewModel.instance
                        offerViewModel.departmentOrganisation = departmentOrganisation.value
                    }

                    CustomTextFieldMaxChar(
                        descriptionState = departmentOrganisation,
                        maxCharacters = 1000,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Text
                        )
                    )

                    // Sections spacer
                    Spacer(modifier = Modifier.height(16.dp))

                    // Buttons - Previous + Next
                    Box(
                        modifier = Modifier
                            .fillMaxWidth(),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            CustomButton(
                                onClick = {
                                    navController.navigate("companyPostOfferPage2")
                                },
                                text = stringResource(id = R.string.button_previous_text),
                                modifier = Modifier.weight(1f)
                            )

                            CustomButton(
                                onClick = {
                                    val offerViewModel = OfferViewModel.instance

                                    scope.launch {
                                        val offerPost = OfferPost(
                                            companyName = offerViewModel.companyName,
                                            contractType = offerViewModel.contractType,
                                            departmentOrganisation = offerViewModel.departmentOrganisation,
                                            description = offerViewModel.description,
                                            jobType = offerViewModel.jobType,
                                            recruiterId = offerViewModel.recruiterId,
                                            requirements = offerViewModel.requirements,
                                            responsibilities = offerViewModel.responsibilities,
                                            salaryRange = offerViewModel.salaryRange,
                                            skills = offerViewModel.skills,
                                            location = offerViewModel.location,
                                            title = offerViewModel.title,
                                            workingDay = offerViewModel.workingDay,
                                            workingHours = offerViewModel.workingHours
                                        )

                                        userApiService.addOffer(offerPost)
                                        navController.navigate("offersList")
                                    }
                                },
                                text = stringResource(id = R.string.button_finish_text),
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun CustomCompanyPostOfferPage3Preview() {
//    AppTheme {
//        CompanyPostOfferPage3(rememberNavController())
//    }
//}
