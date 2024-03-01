package cat.dam.grup2.swipe4job_app.login

import android.os.Build
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cat.dam.grup2.swipe4job_app.R
import cat.dam.grup2.swipe4job_app.features.users.UserApiService
import kotlinx.coroutines.launch
import cat.dam.grup2.swipe4job_app.shared_composables.CustomButton
import cat.dam.grup2.swipe4job_app.shared_composables.CustomOutlinedTextField
import cat.dam.grup2.swipe4job_app.shared_composables.IconVector

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun UserLoginForm(navController: NavController, userApiService: UserApiService) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

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
                contentDescription = stringResource(id = R.string.logo_image_description),
                modifier = Modifier
                    .size(250.dp)
                    .align(Alignment.CenterHorizontally) // Centrado horizontalmente
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
                    scope.launch {
                        val data = userApiService.userLogin(username, password)
                        println(data)
                    }
                },
                text = stringResource(id = R.string.button_login_text)
            )

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