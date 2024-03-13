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
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddStudyContent() {
    var study by remember { mutableStateOf("") }
    var school by remember { mutableStateOf("") }
    var startingDate by remember { mutableStateOf("") }
    var endDate by remember { mutableStateOf("") }
    val openStartingDateDialog = remember { mutableStateOf(false) }
    val openEndDateDialog = remember { mutableStateOf(false) }
//    val selectedMonth = remember { mutableStateOf("") }
//    val selectedYear = remember { mutableStateOf("") }
    var monthOptions = stringArrayResource(R.array.months_array).toList()
    val years = (1924..2024).map { it.toString() }.reversed()
    val yearsMap = years.associateWith { it }

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

            TextField(
                value = startingDate,
                onValueChange = { },
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
                onValueChange = { endDate = it },
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

            CustomAlertDialog(
                openDialog = openStartingDateDialog,
                months = monthOptions,
                years = yearsMap,
                onAccept = { startingDate = it }
            )

            CustomAlertDialog(
                openDialog = openEndDateDialog,
                months = monthOptions,
                years = yearsMap,
                onAccept = { endDate = it }
            )
        }
    }
}

@Composable
fun CustomAlertDialog(
    openDialog: MutableState<Boolean>,
    months: List<String>,
    years: Map<String, String>,
    onAccept: (String) -> Unit
) {
    var selectedMonth by remember { mutableStateOf("") }
    var selectedYear by remember { mutableStateOf("") }

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            title = { Text(text = "Seleccione una fecha") },
            text = {
                Column {
                    // Dropdown para los meses
                    CustomDropdown(
                        modifier = Modifier.weight(1f), // Ocupa el 50% del ancho
                        placeholder = stringResource(id = R.string.month_text),
                        items = months,
                        onChange = { month ->
                            selectedMonth = month
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp)) // Espacio entre los dropdowns
                    // Dropdown para los años
                    CustomDropdown(
                        modifier = Modifier.weight(1f), // Ocupa el 50% del ancho
                        placeholder = stringResource(id = R.string.year_text),
                        items = years.keys.toList(),
                        onChange = { year ->
                            selectedYear = year
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp)) // Espacio entre los dropdowns y el botón
                    // Botón de aceptar
                    TextButton(onClick = {
                        openDialog.value = false
                        // Llamar al callback para actualizar las fechas en AddStudyContent
                        val selectedDate = "${selectedMonth} ${selectedYear}"
                        onAccept(selectedDate)
                    }) {
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