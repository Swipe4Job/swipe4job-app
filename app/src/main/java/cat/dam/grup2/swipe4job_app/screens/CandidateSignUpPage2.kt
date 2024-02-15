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
import androidx.compose.material.icons.filled.CalendarToday
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
import cat.dam.grup2.swipe4job_app.composables.CustomDropdown
import cat.dam.grup2.swipe4job_app.composables.CustomOutlinedTextField
import cat.dam.grup2.swipe4job_app.composables.IconVector
import cat.dam.grup2.swipe4job_app.ui.theme.AppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CandidateSignUpPage2(navController: NavController) {
    var birthdate by remember { mutableStateOf("") }
    var postalcode by remember { mutableStateOf("") }
    var town by remember { mutableStateOf("") }

    var provinceText = stringResource(id = R.string.label_province)
    var selectedItem by remember { mutableStateOf(provinceText) }
    val provinces = listOf(
        "Álava",
        "Albacete",
        "Alacant",
        "Almería",
        "Ávila",
        "Badajoz",
        "Illes Balears",
        "Barcelona",
        "Burgos",
        "Cáceres",
        "Cádiz",
        "Castelló",
        "Ciudad Real",
        "Córdoba",
        "A Coruña",
        "Cuenca",
        "Girona",
        "Granada",
        "Guadalajara",
        "Gipuzkoa",
        "Huelva",
        "Huesca",
        "Jaén",
        "León",
        "Lleida",
        "La Rioja",
        "Lugo",
        "Madrid",
        "Málaga",
        "Murcia",
        "Nafarroa",
        "Ourense",
        "Asturias",
        "Palencia",
        "Las Palmas",
        "Pontevedra",
        "Salamanca",
        "Sta. Cruz de Tenerife",
        "Cantabria",
        "Segovia",
        "Sevilla",
        "Soria",
        "Tarragona",
        "Teruel",
        "Toledo",
        "Valéncia",
        "Valladolid",
        "Bizkaia",
        "Zamora",
        "Zaragoza",
        "Ceuta",
        "Melilla"
    )


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

                    // Birth date TextField
                    CustomOutlinedTextField(
                        value = birthdate,
                        onValueChange = { birthdate = it },
                        label = stringResource(id = R.string.label_birthdate),
                        trailingIcon = IconVector.ImageVectorIcon(Icons.Default.CalendarToday),
                        iconContentDescription = stringResource(id = R.string.calendar_icon_description),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Text
                        )
                    )

                    // Spacer
                    Spacer(modifier = Modifier.height(16.dp))

                    // Postal code TextField
                    CustomOutlinedTextField(
                        value = postalcode,
                        onValueChange = { postalcode = it },
                        label = stringResource(id = R.string.label_postalCode),
                        leadingIcon = null,
                        iconContentDescription = null,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Number
                        )
                    )

                    // Spacer
                    Spacer(modifier = Modifier.height(16.dp))

                    // Province dropdown
                    CustomDropdown(
                        placeholder = selectedItem,
                        items = provinces,
                    ) {
                        selectedItem = it
                    }

                    // Spacer
                    Spacer(modifier = Modifier.height(16.dp))

                    // Town TextField
                    CustomOutlinedTextField(
                        value = town,
                        onValueChange = { town = it },
                        label = stringResource(id = R.string.label_town),
                        leadingIcon = IconVector.ImageVectorIcon(Icons.Default.Place),
                        iconContentDescription = stringResource(id = R.string.location_icon_description),
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                            keyboardType = KeyboardType.Number
                        )
                    )


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
                                    navController.navigate("candidateSignUpPage1")
                                },
                                text = stringResource(id = R.string.button_previous_text),
                                modifier = Modifier.weight(1f)
                            )

                            CustomButton(
                                onClick = {
                                    navController.navigate("candidateSignUpPage3")
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
fun CustomCandidateSignUpPage2Preview() {
    AppTheme {
        CandidateSignUpPage2(rememberNavController())
    }
}
