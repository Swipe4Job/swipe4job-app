package cat.dam.grup2.swipe4job_app.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.res.painterResource
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
import com.google.mlkit.vision.text.Text

@Composable
fun RolSelection() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // App logo
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(250.dp)
                    .align(Alignment.CenterHorizontally) // Centrado horizontalmente
            )

            OutlinedButton(
                onClick = { /* Handle the login action here */ },
                modifier = Modifier
                    .wrapContentSize() // Ajusta el tamaño del botón al contenido
                    .padding(8.dp),

                content = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally, // Alinea el contenido al centro
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.candidate),
                            contentDescription = null,
                            modifier = Modifier.size(50.dp)
                        )
                        Text(
                            text = "Candidate",
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            )


            OutlinedButton(
                onClick = { /* Handle the login action here */ },
                modifier = Modifier
                    .wrapContentSize() // Ajusta el tamaño del botón al contenido
                    .padding(8.dp),

                content = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally, // Alinea el contenido al centro
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.recruiter),
                            contentDescription = null,
                            modifier = Modifier.size(50.dp)
                        )
                        Text(
                            text = "Recruiter",
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            )



            // Title - Rol selection
            Text(
                stringResource(id = R.string.rolSelection_text),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondary
            )

            // Spacer
            Spacer(modifier = Modifier.height(16.dp))

            // Login Button
            CustomButton(
                onClick = {
                    // Lógica a realizar al hacer clic en el botón
                },
                text = stringResource(id = R.string.button_login_text)
            )

            // Spacer
            Spacer(modifier = Modifier.height(16.dp))

            // Signup Button
            CustomButton(
                onClick = {
                    // Lógica a realizar al hacer clic en el botón
                },
                text = stringResource(id = R.string.button_signup_text)
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CustomRolSelectionPreview() {
    AppTheme {
        RolSelection()
    }
}