package cat.dam.grup2.swipe4job_app.screens

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import cat.dam.grup2.swipe4job_app.R
import cat.dam.grup2.swipe4job_app.composables.CustomButton
import cat.dam.grup2.swipe4job_app.composables.CustomTextFieldMaxChar
import cat.dam.grup2.swipe4job_app.ui.theme.AppTheme

@SuppressLint("UnrememberedMutableState")
@Composable
fun RecruiterSignUpPage3(navController: NavController) {

    var acceptedTerms by remember { mutableStateOf(false) }
    var acceptedEmailPolicy by remember { mutableStateOf(false) }

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
                //.verticalScroll(enabled = true, state = rememberScrollState())
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    // Steps number
                    Text(
                        "2/3",
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .background(Color.Transparent)
                            .align(Alignment.End)
                    )

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
                    var companyDescription by remember { mutableStateOf("") }

                    CustomTextFieldMaxChar(
                        descriptionState = mutableStateOf(companyDescription),
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
                                    navController.navigate("recruiterSignUpPage2")
                                },
                                text = stringResource(id = R.string.button_previous_text),
                                modifier = Modifier.weight(1f)
                            )

                            CustomButton(
                                onClick = {
                                    /*TODO*/
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CustomRecruiterSignUpPage3Preview() {
    AppTheme {
        RecruiterSignUpPage3(rememberNavController())
    }
}