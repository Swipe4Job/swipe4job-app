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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import cat.dam.grup2.swipe4job_app.R
import cat.dam.grup2.swipe4job_app.shared.composables.CustomDropdown
import cat.dam.grup2.swipe4job_app.shared.composables.CustomOutlinedTextField
import cat.dam.grup2.swipe4job_app.shared.composables.IconVector

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AddStudy(navController: NavController) {
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
                            text = stringResource(id = R.string.addStudy_text),
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
                                    /* TODO: Save data */
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
                AddStudyContent()
            }
        }
    }
}

@Composable
fun AddStudyContent() {
    var study by remember { mutableStateOf("") }
    var school by remember { mutableStateOf("") }
    var startingDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }
    val openDialog = remember { mutableStateOf(false) }
    val selectedDate = remember { mutableStateOf("") }
    var month = stringResource(id = R.string.month_text)
    var selectedMonthItem by remember { mutableStateOf(month) }
    var monthOptions = stringArrayResource(R.array.months_array).toList()
    var year = stringResource(id = R.string.year_text)
    var selectedYearItem by remember { mutableStateOf(year) }
    val years = (1024..2024).map { it.toString() }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            // Title - Studies name
            Text(
                stringResource(id = R.string.studiesName_text),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Studies TextField
            CustomOutlinedTextField(
                value = study,
                onValueChange = { study = it },
                label = stringResource(id = R.string.label_study),
                leadingIcon = null,
                iconContentDescription = null,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Title - School
            Text(
                stringResource(id = R.string.school_text),
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // School TextField
            CustomOutlinedTextField(
                value = school,
                onValueChange = { school = it },
                label = stringResource(id = R.string.label_school),
                leadingIcon = null,
                iconContentDescription = null,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
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
            CustomOutlinedTextField(
                value = startingDate,
                onValueChange = { startingDate = it },
                label = stringResource(id = R.string.label_startingDate),
                leadingIcon = IconVector.ImageVectorIcon(Icons.Default.CalendarToday),
                iconContentDescription = stringResource(id = R.string.calendar_icon_description),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.clickable { openDialog.value = true }
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
            CustomOutlinedTextField(
                value = endDate,
                onValueChange = { endDate = it },
                label = stringResource(id = R.string.label_endDate),
                leadingIcon = IconVector.ImageVectorIcon(Icons.Default.CalendarToday),
                iconContentDescription = stringResource(id = R.string.calendar_icon_description),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                    keyboardType = KeyboardType.Number
                ),
                modifier = Modifier.clickable { openDialog.value = true }
            )

            CustomAlertDialog(
                openDialog = openDialog,
                selectedDate = selectedDate,
                months = monthOptions,
                years = years )
        }
    }
}

@Composable
fun CustomAlertDialog(
    openDialog: MutableState<Boolean>,
    selectedDate: MutableState<String>,
    months: List<String>,
    years: List<String>
) {
    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = { Text(text = "Seleccione una fecha") },
            text = {
                Column {
                    Row(Modifier.fillMaxWidth()) {
                        CustomDropdown(
                            placeholder = stringResource(id = R.string.month_text),
                            items = months,
                            onChange = { month ->
                                selectedDate.value = "$month ${selectedDate.value.split(" ")[1]}"
                            }
                        )
                        CustomDropdown(
                            placeholder = stringResource(id = R.string.year_text),
                            items = years,
                            onChange = { year ->
                                selectedDate.value = "${selectedDate.value.split(" ")[0]} $year"
                            }
                        )
                    }
                    TextButton(onClick = { openDialog.value = false }) {
                        Text("Aceptar")
                    }
                }
            },
            confirmButton = { }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddStudyPreview() {
    AddStudy(rememberNavController())
}