package cat.dam.grup2.swipe4job_app.shared.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.EaseOutElastic
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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

    val barScale by animateFloatAsState(
        targetValue = if (isBarAnimationLaunched) 0.8f else 0f,
        animationSpec = tween(
            durationMillis = 500,
            easing = EaseOutElastic
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
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier
                    .graphicsLayer(scaleX = logoScale, scaleY = logoScale)
                    .animateContentSize(),
                painter = painterResource(id = getIconResource()),
                contentDescription = stringResource(R.string.icon_logo_text)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                modifier = Modifier
                    .graphicsLayer(scaleX = barScale, scaleY = barScale)
                    .animateContentSize(),
                painter = painterResource(id = getBarResource()),
                contentDescription = stringResource(R.string.icon_logo_text)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                modifier = Modifier
                    .graphicsLayer(scaleX = textScale, scaleY = textScale)
                    .animateContentSize(),
                painter = painterResource(id = getTextResource()),
                contentDescription = stringResource(R.string.icon_logo_text)
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


private fun getIconResource(): Int {
    // TODO check dark or light theme
    return R.drawable.icon_dark
}

private fun getBarResource(): Int {
    return R.drawable.bar_dark
}

private fun getTextResource(): Int {
    return R.drawable.text_dark
}