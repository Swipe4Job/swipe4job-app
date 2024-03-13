package cat.dam.grup2.swipe4job_app.shared.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.EaseOutElastic
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cat.dam.grup2.swipe4job_app.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SplashScreen(navController: NavController) {
    val scope = rememberCoroutineScope()
    val maxScale = .9f
    var isLogoAnimationLaunched by remember { mutableStateOf(false) }
    var isBarAnimationLaunched by remember { mutableStateOf(false) }
    var isTextAnimationLaunched by remember { mutableStateOf(false) }

    val logoScale by animateFloatAsState(
        targetValue = if (isLogoAnimationLaunched) 0.8f else 0f,
        animationSpec = tween(
            durationMillis = 500,
            easing = EaseOutElastic
        ),
        label = "Elastic animation",
        finishedListener = {
            scope.launch {
                isBarAnimationLaunched = true
            }
        }
    )

    val barRotation by animateFloatAsState(
        targetValue = if (isBarAnimationLaunched) 360f else 0f,
        animationSpec = tween(
            durationMillis = 1000
        ),
        label = "Elastic animation",
        finishedListener = {
            scope.launch {
                isTextAnimationLaunched = true
            }
        }
    )

    val textScale by animateFloatAsState(
        targetValue = if (isTextAnimationLaunched) maxScale else 0f,
        animationSpec = tween(
            durationMillis = 500,
            easing = EaseOutElastic
        ),
        finishedListener = {
            scope.launch {
                delay(500)
                navController.navigate("userLoginForm")
            }
        },
        label = "Elastic animation",
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier
                    .graphicsLayer(scaleX = logoScale, scaleY = logoScale)
                    .animateContentSize(),
                painter = getIconResource(),
                contentDescription = stringResource(R.string.logo_image_description)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                modifier = Modifier
                    .graphicsLayer(rotationZ = barRotation)
                    .animateContentSize(),
                painter = getBarResource(),
                contentDescription = stringResource(R.string.logo_image_description)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                modifier = Modifier
                    .graphicsLayer(scaleX = textScale, scaleY = textScale)
                    .animateContentSize(),
                painter = getTextResource(),
                contentDescription = stringResource(R.string.logo_image_description)
            )
        }
    }

    SideEffect {
        scope.launch {
            delay(timeMillis = 500)
            isLogoAnimationLaunched = true
        }
    }
}

@Composable
private fun getIconResource(): Painter {
    return if (isSystemInDarkTheme()) {
        painterResource(id = R.drawable.icon_dark)
    } else {
        painterResource(id = R.drawable.icon_light)
    }
}

@Composable
private fun getBarResource(): Painter {
    return if (isSystemInDarkTheme()) {
        painterResource(id = R.drawable.bar_dark)
    } else {
        painterResource(id = R.drawable.bar_light)
    }
}

@Composable
private fun getTextResource(): Painter {
    return if (isSystemInDarkTheme()) {
        painterResource(id = R.drawable.text_dark)
    } else {
        painterResource(id = R.drawable.text_light)
    }
}