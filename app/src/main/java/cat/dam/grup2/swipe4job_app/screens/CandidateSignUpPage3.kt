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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cat.dam.astrodev.navigation_template.R
//import cat.dam.grup2.swipe4job_app.R

@Composable
fun CandidateSignUpPage3() {

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
                "3/3",
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .background(Color.Transparent)
                    .align(Alignment.End)
            )
            Image(
                painter = painterResource(id = R.drawable.curriculum),
                contentDescription = "Profile Image",
                modifier = Modifier
                    .size(250.dp)
//                            .clip(CircleShape)
                    .align(Alignment.CenterHorizontally) // Centrado horizontalmente
            )
            Text(
                text = "Upload your CV and save time",
                style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "If you have a CV in PDF or Word format, upload it and we will autocomplete the data from your profile in an authomatic way.",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedButton(
                onClick = { /* Handle the login action here */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .clipToBounds()
                    .padding(8.dp)
                    .border(
                        BorderStroke(2.dp, color = MaterialTheme.colorScheme.primary),
                        shape = CircleShape
                    )
                    .clip(CircleShape),
                //.dashedBorder(1.dp, MaterialTheme.colorScheme.primary, 8.dp), // Clip to make the corners of the border rounded
                content = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.UploadFile,
                            contentDescription = null,
                            modifier = Modifier.size(50.dp),
                            tint = MaterialTheme.colorScheme.secondary
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            "Upload Curriculum",
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            )
        }

        // Espaciador entre recuadros de texto y botones
        Button(
            onClick = { /* Handle the login action here */ },
            modifier = Modifier
                .align(Alignment.BottomCenter)
        ) {
            Text("Not now")
        }
    }
}


fun Modifier.dashedBorder(strokeWidth: Dp, color: Color, cornerRadiusDp: Dp) = composed(
    factory = {
        val density = LocalDensity.current
        val strokeWidthPx = density.run { strokeWidth.toPx() }
        val cornerRadiusPx = density.run { cornerRadiusDp.toPx() }

        this.then(
            Modifier.drawWithCache {
                onDrawBehind {
                    val stroke = Stroke(
                        width = strokeWidthPx,
                        pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
                    )

                    drawRoundRect(
                        color = color,
                        style = stroke,
                        cornerRadius = CornerRadius(cornerRadiusPx)
                    )
                }
            }
        )
    }
)