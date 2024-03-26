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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cat.dam.grup2.swipe4job_app.R
import cat.dam.grup2.swipe4job_app.features.users.user_api_service.UserApiService
import cat.dam.grup2.swipe4job_app.shared.composables.CustomButton
import cat.dam.grup2.swipe4job_app.shared.composables.CustomDropdown
import cat.dam.grup2.swipe4job_app.shared.composables.CustomOutlinedTextField
import cat.dam.grup2.swipe4job_app.shared.composables.CustomTextFieldMaxChar
import cat.dam.grup2.swipe4job_app.shared.composables.IconVector
import cat.dam.grup2.swipe4job_app.features.recruiter.models.CompanyPost
import cat.dam.grup2.swipe4job_app.features.recruiter.models.companySectorFromStringResource
import cat.dam.grup2.swipe4job_app.features.recruiter.models.companySizeFromStringResource
import kotlinx.coroutines.launch

@Composable
fun RecruiterSignUpPage2(navController: NavController, userApiService: UserApiService) {
    var companyName by remember { mutableStateOf("") }
    var companyPhoneNumber by remember { mutableStateOf("") }
    var nif by remember { mutableStateOf("") }
    var sectorText = stringResource(id = R.string.label_companySector)
    var selectedSectorItem by remember { mutableStateOf(sectorText) }
    val sectors = stringArrayResource(R.array.sectors_array).toList()
    var companySizeText = stringResource(id = R.string.label_companySize)
    var selectedCompanySizeItem by remember { mutableStateOf(companySizeText) }
    var companySizeOptions =  stringArrayResource(R.array.company_size_array).toList()
    var companyDescription by remember { mutableStateOf(mutableStateOf("")) }
    var acceptedTerms by remember { mutableStateOf(false) }
    var acceptedEmailPolicy by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

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
                        "2/2",
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
                            keyboardType = KeyboardType.Text
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

                    // Title - Description
                    Text(
                        stringResource(id = R.string.companyDescription_text),
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier
                            .padding(bottom = 12.dp)
                    )

                    // Text field for the description
                    CustomTextFieldMaxChar(
                        descriptionState = companyDescription,
                        maxCharacters = 1000,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Text
                        )
                    )

                    // Sections spacer
                    Spacer(modifier = Modifier.height(16.dp))

                    // Checkboxes
                    Column {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Checkbox(
                                checked = acceptedTerms,
                                onCheckedChange = { acceptedTerms = it },
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                stringResource(id = R.string.acceptedTerms_text),
                                color = MaterialTheme.colorScheme.onBackground,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            Checkbox(
                                checked = acceptedEmailPolicy,
                                onCheckedChange = { acceptedEmailPolicy = it },
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                stringResource(id = R.string.acceptedEmailPolicy_text),
                                color = MaterialTheme.colorScheme.onBackground,
                                style = MaterialTheme.typography.bodySmall
                            )
                        }
                    }

                    // Buttons - Previous + Finish
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
                                    scope.launch {

                                        val companyPost = CompanyPost(
                                            CIF = nif,
                                            companySize = context.companySizeFromStringResource(selectedCompanySizeItem),
                                            description = companyDescription.value,
                                            name = companyName,
                                            phone = companyPhoneNumber,
                                            sector = context.companySectorFromStringResource(selectedSectorItem)
                                        )
                                        println(companyPost)
                                        try {
                                            userApiService.addCompany(companyPost)
                                            navController.navigate("candidateSimpleDetails")
                                        } catch (e: Exception) {
                                            println(e)
                                            Toast.makeText(context, "Cannot create company", Toast.LENGTH_SHORT).show()
                                        }
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
//fun CustomRecruiterSignUpPage2Preview() {
//    AppTheme {
//        RecruiterSignUpPage2(rememberNavController())
//    }
//}
