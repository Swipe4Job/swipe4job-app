package cat.dam.grup2.swipe4job_app.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cat.dam.grup2.swipe4job_app.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CandidateSignUpPage2() {
    var birthdate by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var telephone by remember { mutableStateOf("") }
    var postalcode by remember { mutableStateOf("") }
    var province by remember { mutableStateOf("") }
    var population by remember { mutableStateOf("") }
    var switchState by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(enabled = true, state = rememberScrollState())
        ) {
            Text(
                "2/3",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .background(Color.Transparent)
                    .align(Alignment.End)
            )
            // Cuatro recuadros de texto uno debajo del otro
            OutlinedTextField(
                value = birthdate,
                onValueChange = { birthdate = it },
                label = { Text("Birth date") },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.CalendarToday,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
            OutlinedTextField(
                value = gender,
                onValueChange = { gender = it },
                label = { Text("Gender") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { /* Handle the login action here */ }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
            OutlinedTextField(
                value = telephone,
                onValueChange = { telephone = it },
                label = { Text("Phone number") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Call,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { /* Handle the login action here */ }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(8.dp),
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Text("I reside in Spain")
//                        Switch(
//                            checked = switchState,
//                            onCheckedChange = { newState -> switchState = newState },
//                            modifier = Modifier.padding(start = 80.dp)
//                        )
//                    }

            OutlinedTextField(
                value = postalcode,
                onValueChange = { postalcode = it },
                label = { Text("Postal Code") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { /* Handle the login action here */ }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    containerColor = MaterialTheme.colorScheme.background
                )
            )

            OutlinedTextField(
                value = province,
                onValueChange = { province = it },
                label = { Text("Province") },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { /* Handle the login action here */ }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    containerColor = MaterialTheme.colorScheme.background
                )
            )

            OutlinedTextField(
                value = population,
                onValueChange = { population = it },
                label = { Text("City") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Place,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onBackground
                    )
                },
                keyboardOptions = KeyboardOptions.Default.copy(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { /* Handle the login action here */ }
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
        // Buttons - Previous + Next
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = { /* Acción del primer botón */ }) {
                Text("Previous")
            }

            Button(onClick = { /* Acción del segundo botón */ }) {
                Text("Next")
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CustomCandidateSignUpPage2Preview() {
    AppTheme {
        CandidateSignUpPage2()
    }
}
