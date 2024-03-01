package cat.dam.grup2.swipe4job_app.features.users.screens.login

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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import cat.dam.grup2.swipe4job_app.R
import cat.dam.grup2.swipe4job_app.shared.ui.theme.AppTheme

@Composable
fun RolSelection(navController: NavController) {
    var rol by remember { mutableStateOf("") }

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
            verticalArrangement = Arrangement.Top
        ) {
            // App logo
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(id = R.string.logo_image_description),
                modifier = Modifier
                    .size(250.dp)
                    .align(Alignment.CenterHorizontally)
            )

            // Spacer
            Spacer(modifier = Modifier.height(16.dp))

            // Title - Rol selection
            Text(
                stringResource(id = R.string.rolSelection_text),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.secondary
            )

            // Spacer
            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    OutlinedButton(
                        onClick = {
                            navController.navigate("candidateSignUpPage1")
                        },
                        modifier = Modifier
                            .wrapContentSize()
                            .padding(8.dp)
                            .border(
                                BorderStroke(2.dp, color = MaterialTheme.colorScheme.primary),
                                shape = CircleShape
                            ),

                        content = {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.candidat_light_theme),
                                    contentDescription = null,
                                    modifier = Modifier.size(50.dp)
                                )
                                Text(
                                    text = stringResource(id = R.string.candidate_Text),
                                    color = MaterialTheme.colorScheme.secondary
                                )
                            }
                        }
                    )


                    OutlinedButton(
                        onClick = {
                            navController.navigate("recruiterSignUpPage1")
                        },
                        modifier = Modifier
                            .wrapContentSize() // Ajusta el tamaño del botón al contenido
                            .padding(8.dp)
                            .border(
                                BorderStroke(2.dp, color = MaterialTheme.colorScheme.primary),
                                shape = CircleShape
                            ),

                        content = {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.recruiter_light_theme),
                                    contentDescription = null,
                                    modifier = Modifier.size(50.dp)
                                )
                                Text(
                                    text = stringResource(id = R.string.recruiter_text),
                                    color = MaterialTheme.colorScheme.secondary
                                )
                            }
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CustomRolSelectionPreview() {
    AppTheme {
        RolSelection(rememberNavController())
    }
}