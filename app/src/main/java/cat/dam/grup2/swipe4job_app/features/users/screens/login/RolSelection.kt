package cat.dam.grup2.swipe4job_app.features.users.screens.login

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import cat.dam.grup2.swipe4job_app.R
import cat.dam.grup2.swipe4job_app.shared.composables.CustomButton
import cat.dam.grup2.swipe4job_app.ui.theme.AppTheme

@Composable
fun RolSelection(navController: NavController) {
    var selectedRol by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    var isLoading by remember {
        mutableStateOf(false)
    }
    var isLoading1 by remember {
        mutableStateOf(false)
    }
    val candidateScale by animateFloatAsState(
        targetValue = if (isLoading) 1.2f else 1f,
        animationSpec = tween(durationMillis = 300),
        label = "candidate scale animation",
        finishedListener = {
            navController.navigate("candidateSignUpPage1")
        }
    )
    val recruiterScale by animateFloatAsState(
        targetValue = if (isLoading1) 1.2f else 1f,
        animationSpec = tween(durationMillis = 300),
        label = "recruiter scale animation",
        finishedListener = {
            navController.navigate("recruiterSignUpPage1")
        }
    )
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        item {
            // App logo
            Image(
                if (isSystemInDarkTheme()) {
                    painterResource(id = R.drawable.full_logo_dark)
                } else {
                    painterResource(id = R.drawable.full_logo_light)
                },
                contentDescription = stringResource(id = R.string.logo_image_description),
                modifier = Modifier
                    .size(250.dp)
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

            // Rol selection buttons row
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                OutlinedButton(
                    onClick = {
                        isLoading = true
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
                            // Candidate image with scaling animation
                            Image(
                                if (isSystemInDarkTheme()) {
                                    painterResource(id = R.drawable.candidate_dark_theme)
                                } else {
                                    painterResource(id = R.drawable.candidate_light_theme)
                                },
                                contentDescription = null,
                                modifier = Modifier
                                    .size(50.dp)
                                    .scale(candidateScale) // Use the animated scale factor
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
                        isLoading1 = true
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
                            // Recruiter image with scaling animation
                            Image(
                                if (isSystemInDarkTheme()) {
                                    painterResource(id = R.drawable.recruiter_dark_theme)
                                } else {
                                    painterResource(id = R.drawable.recruiter_light_theme)
                                },
                                contentDescription = null,
                                modifier = Modifier
                                    .size(50.dp)
                                    .scale(recruiterScale)
                            )
                            Text(
                                text = stringResource(id = R.string.recruiter_text),
                                color = MaterialTheme.colorScheme.secondary
                            )
                        }
                    }
                )
            }
            // Spacer
            Spacer(modifier = Modifier.height(16.dp))

            // Already have an account button row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                CustomButton(
                    onClick = {
                        navController.navigate("userLoginForm")
                    },
                    text = stringResource(id = R.string.alreadyHaveAnAccount_text)
                )
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