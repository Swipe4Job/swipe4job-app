package cat.dam.grup2.swipe4job_app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cat.dam.grup2.swipe4job_app.R
import cat.dam.grup2.swipe4job_app.composables.CustomButton
import cat.dam.grup2.swipe4job_app.composables.CustomOutlinedTextField
import cat.dam.grup2.swipe4job_app.composables.IconVector
import cat.dam.grup2.swipe4job_app.ui.theme.AppTheme

@Composable
fun CandidateSignUpPage1() {
    var name by remember { mutableStateOf("") }
    var lastname by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

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
                        "1/3",
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .background(Color.Transparent)
                            .align(Alignment.End)
                    )

                    // Spacer
                    Spacer(modifier = Modifier.height(16.dp))

                    // Name TextField
                    CustomOutlinedTextField(
                        value = name,
                        onValueChange = { name = it },
                        label = stringResource(id = R.string.label_name),
                        leadingIcon = IconVector.ImageVectorIcon(Icons.Default.Person),
                        iconContentDescription = stringResource(id = R.string.user_icon_description),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Text
                        )
                    )

                    // Spacer
                    Spacer(modifier = Modifier.height(16.dp))

                    // Last name TextField
                    CustomOutlinedTextField(
                        value = lastname,
                        onValueChange = { lastname = it },
                        label = stringResource(id = R.string.label_lastname),
                        leadingIcon = null,
                        iconContentDescription = null,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Text
                        )
                    )

                    // Spacer
                    Spacer(modifier = Modifier.height(16.dp))

                    // Email TextField
                    CustomOutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = stringResource(id = R.string.label_username),
                        leadingIcon = IconVector.ImageVectorIcon(Icons.Default.Email),
                        iconContentDescription = stringResource(id = R.string.user_icon_description),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Text
                        )
                    )

                    // Spacer
                    Spacer(modifier = Modifier.height(16.dp))

                    // Password TextField
                    CustomOutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = stringResource(id = R.string.label_password),
                        leadingIcon = IconVector.ImageVectorIcon(Icons.Default.Password),
                        iconContentDescription = stringResource(id = R.string.password_icon_description),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Password
                        ),
                        visualTransformation = PasswordVisualTransformation()
                    )

                    // Spacer
                    Spacer(modifier = Modifier.height(16.dp))

                    // Buttons - Previous + Next
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp),
                        verticalArrangement = Arrangement.Bottom
                    ) {

                        // Create account Button
                        CustomButton(
                            onClick = {
                                // Lógica a realizar al hacer clic en el botón
                            },
                            text = stringResource(id = R.string.button_createAccount_text)
                        )

                        // Already have an account Button
                        CustomButton(
                            onClick = {
                                // Lógica a realizar al hacer clic en el botón
                            },
                            text = stringResource(id = R.string.button_alreadyHaveAccount_text)
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CustomCandidateSignUpPage1Preview() {
    AppTheme {
        CandidateSignUpPage1()
    }
}
