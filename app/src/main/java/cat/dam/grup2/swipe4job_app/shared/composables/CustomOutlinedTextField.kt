package cat.dam.grup2.swipe4job_app.shared.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.VisualTransformation

sealed class IconVector {
    data class ImageVectorIcon(val imageVector: ImageVector) : IconVector()
    data class PainterIcon(val painter: Painter) : IconVector()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomOutlinedTextField(
    value: String,
    onValueChange: (item: String) -> Unit,
    label: String,
    leadingIcon: IconVector? = null,
    trailingIcon: IconVector? = null,
    iconContentDescription: String?,
    keyboardOptions: KeyboardOptions,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        visualTransformation = visualTransformation,
        leadingIcon = {
            leadingIcon?.let { icon ->
                when (icon) {
                    is IconVector.ImageVectorIcon -> {
                        Icon(
                            imageVector = icon.imageVector,
                            contentDescription = iconContentDescription ?: "",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }

                    is IconVector.PainterIcon -> {
                        Icon(
                            painter = icon.painter,
                            contentDescription = iconContentDescription ?: "",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }
        },
        trailingIcon = {
            trailingIcon?.let { icon ->
                when (icon) {
                    is IconVector.ImageVectorIcon -> {
                        Icon(
                            imageVector = icon.imageVector,
                            contentDescription = iconContentDescription ?: "",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }

                    is IconVector.PainterIcon -> {
                        Icon(
                            painter = icon.painter,
                            contentDescription = iconContentDescription ?: "",
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }
                }
            }
        },
        modifier = modifier
            .fillMaxWidth(),
        colors = TextFieldDefaults.textFieldColors(
            unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
            focusedIndicatorColor = MaterialTheme.colorScheme.secondary,
            containerColor = MaterialTheme.colorScheme.background
        ),
        keyboardOptions = keyboardOptions,
        singleLine = true
    )
}
