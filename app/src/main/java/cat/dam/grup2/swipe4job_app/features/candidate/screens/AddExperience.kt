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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import cat.dam.grup2.swipe4job_app.R
import cat.dam.grup2.swipe4job_app.features.candidate.state.CandidateProfileViewModel
import cat.dam.grup2.swipe4job_app.shared.composables.CustomDateSelectionAlertDialog
import cat.dam.grup2.swipe4job_app.shared.composables.CustomOutlinedTextField

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AddExperience(navController: NavController) {
    var position by remember { mutableStateOf("") }
    var company by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedStartingDate by remember { mutableStateOf("") }
    var selectedEndDate by remember { mutableStateOf("") }
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
                            text = stringResource(id = R.string.addJobExperience_text),
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
                                    experiencesList.add(
                                        JobExperience(
                                            position,
                                            company,
                                            description,
                                            selectedStartingDate,
                                            selectedEndDate
                                        )
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
                AddExperienceContent(
                    onPositionChange = { position = it },
                    onCompanyChange = { company = it },
                    onStartingDateChange = { selectedStartingDate = it },
                    onEndDateChange = { selectedEndDate = it },
                    onDescriptionChange = { description = it }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExperienceContent(
    onPositionChange: (String) -> Unit,
    onCompanyChange: (String) -> Unit,
    onDescriptionChange: (String) -> Unit,
    onStartingDateChange: (String) -> Unit,
    onEndDateChange: (String) -> Unit
) {
    var position by remember { mutableStateOf("") }
    var company by remember { mutableStateOf("") }
    var startingDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }
    val openStartingDateDialog = remember { mutableStateOf(false) }
    val openEndDateDialog = remember { mutableStateOf(false) }
    var monthOptions = stringArrayResource(R.array.months_array).toList()
    val years = (1924..2024).map { it.toString() }.reversed()
    val yearsMap = years.associateWith { it }
    var description by remember { mutableStateOf("") }

    val context = LocalContext.current

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
                value = position,
                onValueChange = {
                    position = it
                    onPositionChange(it)
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
                value = company,
                onValueChange = {
                    company = it
                    onCompanyChange(it)
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
                value = description,
                onValueChange = {
                    description = it
                    onDescriptionChange(it)
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
                value = startingDate,
                onValueChange = {
                    startingDate = it
                    onStartingDateChange(it)
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
                value = endDate,
                onValueChange = {
                    endDate = it
                    onEndDateChange(it)
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
                    startingDate = it
                    onStartingDateChange(it)
                }
            )

            CustomDateSelectionAlertDialog(
                openDialog = openEndDateDialog,
                months = monthOptions,
                years = yearsMap,
                onAccept = {
                    endDate = it
                    onEndDateChange(it)
                }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddExperiencePreview() {
    AddExperience(rememberNavController())
}