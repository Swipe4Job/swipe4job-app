package cat.dam.grup2.swipe4job_app.screens

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import cat.dam.grup2.swipe4job_app.R
import cat.dam.grup2.swipe4job_app.composables.CustomButton
import cat.dam.grup2.swipe4job_app.composables.CustomDropdown
import cat.dam.grup2.swipe4job_app.composables.CustomOutlinedTextField
import cat.dam.grup2.swipe4job_app.composables.IconVector
import cat.dam.grup2.swipe4job_app.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun RecruiterSignUpPage2(navController: NavController) {
    var companyName by remember { mutableStateOf("") }
    var companyPhoneNumber by remember { mutableStateOf("") }
    var nif by remember { mutableStateOf("") }
    var sectorText = stringResource(id = R.string.label_companySector)
    var selectedSectorItem by remember { mutableStateOf(sectorText) }
    val sectors = stringArrayResource(R.array.sectors_array).toList()
    var companySizeText = stringResource(id = R.string.label_companySize)
    var selectedCompanySizeItem by remember { mutableStateOf(companySizeText) }
    var companySizeOptions =  stringArrayResource(R.array.company_size_array).toList()

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
                    Text(
                        "2/3",
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .background(Color.Transparent)
                            .align(Alignment.End)
                    )

                    // Spacer
                    Spacer(modifier = Modifier.height(16.dp))

                    // Section title
                    Text(
                        stringResource(id = R.string.companyData_text),
                        style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    )

                    // Title - Company name
                    Text(
                        stringResource(id = R.string.companyName_text),
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.titleMedium
                    )

                    // Company name TextField
                    CustomOutlinedTextField(
                        value = companyName,
                        onValueChange = { companyName = it },
                        label = stringResource(id = R.string.label_companyName),
                        leadingIcon = null,
                        iconContentDescription = null,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Number
                        )
                    )

                    // Spacer
                    Spacer(modifier = Modifier.height(16.dp))

                    // Title - Company phone number
                    Text(
                        stringResource(id = R.string.contactPhoneNumber_text),
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.titleMedium
                    )

                    // Company phone number TextField
                    CustomOutlinedTextField(
                        value = companyPhoneNumber,
                        onValueChange = { companyPhoneNumber = it },
                        label = stringResource(id = R.string.label_companyPhoneNumber),
                        leadingIcon = IconVector.ImageVectorIcon(Icons.Default.Call),
                        iconContentDescription = stringResource(id = R.string.phone_icon_description),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Number
                        )
                    )

                    // Spacer
                    Spacer(modifier = Modifier.height(16.dp))

                    // Title - Company N.I.F.
                    Text(
                        stringResource(id = R.string.nif_text),
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.titleMedium
                    )

                    // NIF TextField
                    CustomOutlinedTextField(
                        value = nif,
                        onValueChange = { nif = it },
                        label = stringResource(id = R.string.label_companyNif),
                        leadingIcon = null,
                        iconContentDescription = null,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Text
                        )
                    )

                    // Spacer
                    Spacer(modifier = Modifier.height(16.dp))

                    // Title - Company Sector
                    Text(
                        stringResource(id = R.string.companySector_text),
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.titleMedium
                    )

                    // Sector dropdown
                    CustomDropdown(
                        placeholder = selectedSectorItem,
                        items = sectors
                    ) {
                        selectedSectorItem = it
                    }

                    // Spacer
                    Spacer(modifier = Modifier.height(16.dp))

                    // Title - Company size
                    Text(
                        stringResource(id = R.string.companySize_text),
                        color = MaterialTheme.colorScheme.secondary,
                        style = MaterialTheme.typography.titleMedium
                    )

                    // Company size dropdown
                    CustomDropdown(
                        placeholder = selectedCompanySizeItem,
                        items = companySizeOptions
                    ) {
                        selectedCompanySizeItem = it
                    }

                    // Spacer
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
                                    navController.navigate("recruiterSignUpPage1")
                                },
                                text = stringResource(id = R.string.button_previous_text),
                                modifier = Modifier.weight(1f)
                            )

                            CustomButton(
                                onClick = {
                                    navController.navigate("recruiterSignUpPage3")
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
fun CustomRecruiterSignUpPage2Preview() {
    AppTheme {
        RecruiterSignUpPage2(rememberNavController())
    }
}
