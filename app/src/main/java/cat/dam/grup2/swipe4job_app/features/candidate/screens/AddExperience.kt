package cat.dam.grup2.swipe4job_app.features.candidate.screens

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import cat.dam.grup2.swipe4job_app.R
import cat.dam.grup2.swipe4job_app.features.candidate.state.AddExperienceViewModel
import cat.dam.grup2.swipe4job_app.features.candidate.state.CandidateProfileViewModel
import cat.dam.grup2.swipe4job_app.features.recruiter.models.JobOfferInformation
import cat.dam.grup2.swipe4job_app.shared.composables.CustomDateSelectionAlertDialog
import cat.dam.grup2.swipe4job_app.shared.composables.CustomOutlinedTextField

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AddExperience(navController: NavController) {
    var addExperienceViewModel = AddExperienceViewModel.instance
    var isEditing = addExperienceViewModel.editingJobExperience != null
    var position = remember {
        mutableStateOf(if (isEditing) addExperienceViewModel.editingJobExperience!!.position else "")
    }
    var company = remember {
        mutableStateOf(if (isEditing) addExperienceViewModel.editingJobExperience!!.company else "")
    }
    var description = remember {
        mutableStateOf(
            if (isEditing) {
                addExperienceViewModel.editingJobExperience!!.description ?: ""
            } else ""
        )
    }
    var selectedStartingDate = remember {
        mutableStateOf(if (isEditing) addExperienceViewModel.editingJobExperience!!.startDate else "")
    }
    var selectedEndDate = remember {
        mutableStateOf(
            if (isEditing) {
                addExperienceViewModel.editingJobExperience!!.endDate ?: ""
            } else ""
        )
    }
    var candidateProfileViewModel = CandidateProfileViewModel.getInstance()
    var experiencesList = candidateProfileViewModel.experiences


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
                            text =
                            if (isEditing) stringResource(id = R.string.editJobExperience_text)
                            else stringResource(id = R.string.addJobExperience_text),
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
                                    var jobExperience =
                                        JobExperience(
                                            position.value,
                                            company.value,
                                            description.value,
                                            selectedStartingDate.value,
                                            selectedEndDate.value
                                        )
                                    if (isEditing) {
                                        experiencesList.set(
                                            addExperienceViewModel.editingIndex,
                                            jobExperience
                                        )
                                    } else {
                                        experiencesList.add(jobExperience)
                                    }
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
                AddExperienceContent(
                    position = position,
                    company = company,
                    startingDate = selectedStartingDate,
                    endDate = selectedEndDate,
                    description = description,
                    experiencesList = experiencesList,
                    navController = navController
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExperienceContent(
    position: MutableState<String>,
    company: MutableState<String>,
    startingDate: MutableState<String>,
    endDate: MutableState<String>,
    description: MutableState<String>,
    experiencesList: MutableList<JobExperience>,
    navController: NavController
) {
    val openStartingDateDialog = remember { mutableStateOf(false) }
    val openEndDateDialog = remember { mutableStateOf(false) }
    var monthOptions = stringArrayResource(R.array.months_array).toList()
    val years = (1924..2024).map { it.toString() }.reversed()
    val yearsMap = years.associateWith { it }
    var addExperienceViewModel = AddExperienceViewModel.instance
    var isEditing = addExperienceViewModel.editingJobExperience != null
    val showDeleteConfirmationDialog = remember { mutableStateOf(false) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            // Title - Position
            Text(
                stringResource(id = R.string.position_text),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Position TextField
            CustomOutlinedTextField(
                value = position.value,
                onValueChange = {
                    position.value = it
                },
                label = stringResource(id = R.string.label_jobTitle),
                leadingIcon = null,
                iconContentDescription = null,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text
                ),
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Title - Company
            Text(
                stringResource(id = R.string.companyName_text),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Company TextField
            CustomOutlinedTextField(
                value = company.value,
                onValueChange = {
                    company.value = it
                },
                label = stringResource(id = R.string.label_companyName),
                leadingIcon = null,
                iconContentDescription = null,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Title - Description
            Text(
                stringResource(id = R.string.description_text),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Description TextField
            CustomOutlinedTextField(
                value = description.value,
                onValueChange = {
                    description.value = it
                },
                label = stringResource(id = R.string.label_description),
                leadingIcon = null,
                iconContentDescription = null,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Title - Starting date
            Text(
                stringResource(id = R.string.startingDate_text),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Starting date TextField

            TextField(
                value = startingDate.value,
                onValueChange = {
                    startingDate.value = it
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.label_startingDate),
                        modifier = Modifier.clickable { openStartingDateDialog.value = true }
                    )
                },
                readOnly = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = stringResource(id = R.string.calendar_icon_description)
                    )
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    focusedIndicatorColor = MaterialTheme.colorScheme.secondary,
                    containerColor = MaterialTheme.colorScheme.background
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Title - End date
            Text(
                stringResource(id = R.string.endDate_text),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // End date TextField

            TextField(
                value = endDate.value,
                onValueChange = {
                    endDate.value = it
                },
                label = {
                    Text(
                        text = stringResource(id = R.string.label_endDate),
                        modifier = Modifier.clickable { openEndDateDialog.value = true }
                    )
                },
                readOnly = true,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = stringResource(id = R.string.calendar_icon_description)
                    )
                },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    focusedIndicatorColor = MaterialTheme.colorScheme.secondary,
                    containerColor = MaterialTheme.colorScheme.background
                )
            )

            CustomDateSelectionAlertDialog(
                openDialog = openStartingDateDialog,
                months = monthOptions,
                years = yearsMap,
                onAccept = {
                    startingDate.value = it
                }
            )

            CustomDateSelectionAlertDialog(
                openDialog = openEndDateDialog,
                months = monthOptions,
                years = yearsMap,
                onAccept = {
                    endDate.value = it
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (isEditing) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.deleteExperience_text),
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .clickable {
                                showDeleteConfirmationDialog.value = true
                            }
                    )
                }
            }
        }
    }

    if (showDeleteConfirmationDialog.value) {
        AlertDialog(
            onDismissRequest = {
                showDeleteConfirmationDialog.value = false
            },
            text = {
                Text(text = "Se va a eliminar ${position.value}")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        experiencesList.removeAt(addExperienceViewModel.editingIndex)
                        showDeleteConfirmationDialog.value = false
                        navController.popBackStack()
                    }
                ) {
                    Text(text = stringResource(id = R.string.delete_text))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDeleteConfirmationDialog.value = false
                    }
                ) {
                    Text(text = stringResource(id = R.string.cancel_text))
                }
            }
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddExperiencePreview() {
    AddExperience(rememberNavController())
}