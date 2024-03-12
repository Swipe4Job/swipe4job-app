package cat.dam.grup2.swipe4job_app.features.users.screens.login

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cat.dam.grup2.swipe4job_app.CustomError
import cat.dam.grup2.swipe4job_app.R
import cat.dam.grup2.swipe4job_app.features.auth.AuthViewModel
import cat.dam.grup2.swipe4job_app.features.users.UserViewModel
import cat.dam.grup2.swipe4job_app.features.users.user_api_service.UserApiService
import cat.dam.grup2.swipe4job_app.features.users.user_api_service.model.UserData
import cat.dam.grup2.swipe4job_app.shared.composables.CustomButton
import cat.dam.grup2.swipe4job_app.shared.composables.CustomOutlinedTextField
import cat.dam.grup2.swipe4job_app.shared.composables.IconVector
import cat.dam.grup2.swipe4job_app.shared.composables.LoadingDialog
import kotlinx.coroutines.launch
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay


@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun UserLoginForm(navController: NavController, userApiService: UserApiService) {
    val currentContext = LocalContext.current
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) } // Flag per indicar si s'està carregant

    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            item {
                // App logo
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = stringResource(id = R.string.logo_image_description),
                    modifier = Modifier
                        .size(250.dp)
                )
                // Username TextField
                CustomOutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    label = stringResource(id = R.string.label_username),
                    leadingIcon = IconVector.ImageVectorIcon(Icons.Default.Person),
                    iconContentDescription = stringResource(id = R.string.user_icon_description),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        keyboardType = KeyboardType.Email
                    )
                )
                
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

                // Login Button
                CustomButton(
                    onClick = {
                        isLoading = true // Estableix la flag a true
                        scope.launch {
                            // Saving data
                            try {
                                val data = userApiService.userLogin(username, password)
                                val authViewModel = AuthViewModel.getInstance()
                                authViewModel.accessToken = data.accessToken
                            } catch (error: CustomError) {
                                isLoading =
                                    false // Un cop finalitzada la crida, establim la flag a false
                                Toast.makeText(
                                    currentContext,
                                    "Cannot log in :(",
                                    Toast.LENGTH_SHORT
                                ).show()
                                return@launch
                            }

                            lateinit var userData: UserData
                            try {
                                userData = userApiService.getMyData()
                                val userViewModel = UserViewModel.getInstance()
                                userViewModel.userData = userData
                            } catch (error: CustomError) {
                                isLoading =
                                    false // Un cop finalitzada la crida, establim la flag a false
                                Toast.makeText(
                                    currentContext,
                                    "Cannot get user info :(",
                                    Toast.LENGTH_SHORT
                                ).show()
                                return@launch
                            }

//                        delay(2000)

                            isLoading =
                                false // Un cop finalitzada la crida, establim la flag a false
                            // Checking the user role
                            if (userData.role == "RECRUITER") {
                                navController.navigate("candidateSimpleDetails")
                            } else if (userData.role == "CANDIDATE") {
                                navController.navigate("jobOfferSimpleDetails")
                            }
                        }
                    },
                    text = stringResource(id = R.string.button_login_text)
                )

                // Mostra l'animació Lottie si isLoading és true
//                if (isLoading) {
//                    // Exemple d'ús de la funció AnimationItem amb l'animació ocupant tota la pantalla
//                    AnimationItem(
//                        animationResId = R.raw.loading_animation,
//                        modifier = Modifier.fillMaxSize() // Modifica per ocupar tota la pantalla
//                    )
//                }
                if (isLoading) {
                    LoadingDialog(onDismiss = { isLoading  = false }) {
//                        delay(3000)
                        isLoading = false
                    }
                }

                // Spacer
                Spacer(modifier = Modifier.height(16.dp))

                // Signup Button
                CustomButton(
                    onClick = {
                        navController.navigate("rolSelection")
                    },
                    text = stringResource(id = R.string.button_signup_text)
                )
            }
        }
    }
}
@Composable
fun AnimationItem(
    animationResId: Int,
    modifier: Modifier = Modifier,
) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(animationResId)
    )
    Box(
        modifier = modifier
            .fillMaxSize() // Modifica per ocupar tota la pantalla
            .aspectRatio(1f)
            .background(Color.Transparent), // Fons transparent per evitar superposicions de colors no desitjats
        contentAlignment = Alignment.Center // Centra el contingut de la Box al mig de la pantalla
    ) {
        LottieAnimation(
            composition = composition,
            modifier = Modifier.fillMaxSize(), // Modifica per ocupar tota la pantalla
            iterations = 10 // Establin un número gran de repeticions per assegurar que mentre està carregant la pàgina s'anirà reproduint l'animació
        )
    }
}
