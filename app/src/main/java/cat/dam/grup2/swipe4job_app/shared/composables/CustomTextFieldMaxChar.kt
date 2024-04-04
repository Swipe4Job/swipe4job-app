package cat.dam.grup2.swipe4job_app.shared.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextFieldMaxChar(
    descriptionState: MutableState<String>,
    maxCharacters: Int,
    keyboardOptions: KeyboardOptions
) {
    BasicTextField(
        value = descriptionState.value,
        onValueChange = { newText ->
            if (newText.length <= maxCharacters) {
                descriptionState.value = newText
            }
        },
        textStyle = MaterialTheme.typography.bodyMedium + LocalTextStyle.current.copy(color = MaterialTheme.colorScheme.onBackground),
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .border(
                1.dp,
                MaterialTheme.colorScheme.primary,
                shape = MaterialTheme.shapes.medium
            )
            .padding(6.dp)
            .height(120.dp),
        keyboardOptions = keyboardOptions,
    )

    // Character counter
    val characterCount = "${descriptionState.value.length}/$maxCharacters"
    Text(
        characterCount,
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.End,
        color = MaterialTheme.colorScheme.primary
    )
}



