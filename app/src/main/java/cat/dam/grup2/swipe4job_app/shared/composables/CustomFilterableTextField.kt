package cat.dam.grup2.swipe4job_app.shared.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomFilterableTextField(
    suggestions: Array<String>,
    onItemSelected: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }
    var filteredSuggestions by remember { mutableStateOf(emptyList<String>()) }
    var isListVisible by remember { mutableStateOf(false) }
    var selectedItemIndex by remember { mutableStateOf(-1) } // Keep the selected index
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        TextField(
            value = text,
            onValueChange = {
                text = it
                filteredSuggestions = suggestions.filter { suggestion ->
                    suggestion.contains(text, ignoreCase = true)
                }

                isListVisible =
                    text.isNotBlank() // Show suggestions only if the text is not empty
            },
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    isListVisible = true
                },
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = MaterialTheme.colorScheme.primary,
                focusedIndicatorColor = MaterialTheme.colorScheme.secondary,
                containerColor = MaterialTheme.colorScheme.secondaryContainer
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Text
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            )
        )

        if (isListVisible) {
            LazyColumn {
                items(filteredSuggestions.size) { index ->
                    val suggestion = filteredSuggestions[index]
                    Text(
                        text = suggestion,
                        modifier = Modifier
                            .padding(8.dp)
                            .clickable {
                                text = suggestion
                                selectedItemIndex =
                                    index // Update the index of the selected item
                                isListVisible = false
                                onItemSelected(suggestion)
                            }
                    )
                }
            }
        }
    }
}