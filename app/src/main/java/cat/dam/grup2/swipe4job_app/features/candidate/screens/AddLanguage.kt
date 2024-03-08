package cat.dam.grup2.swipe4job_app.features.candidate.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import cat.dam.grup2.swipe4job_app.R
import cat.dam.grup2.swipe4job_app.shared.composables.CustomDropdown
import cat.dam.grup2.swipe4job_app.shared.composables.CustomTextFieldMaxChar

@Composable
fun AddLanguage(navController: NavController) {
    var languageText = stringResource(id = R.string.language_text)
    var selectedLanguageItem by remember { mutableStateOf(languageText) }
    var languageOptions = stringArrayResource(R.array.languages_array).toList()

    var levelText = stringResource(id = R.string.level_text)
    var selectedLevelItem by remember { mutableStateOf(levelText) }
    var levelOptions = stringArrayResource(R.array.salary_range_array).toList()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        CustomDropdown(
            placeholder = selectedLanguageItem,
            items = languageOptions
        ) {
            selectedLanguageItem = it
        }

        Spacer(modifier = Modifier.height(16.dp))

        CustomDropdown(
            placeholder = selectedLevelItem,
            items = levelOptions
        ) {
            selectedLevelItem = it
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Title - Comments
        Text(
            stringResource(id = R.string.comments_text),
            color = MaterialTheme.colorScheme.secondary,
            style = MaterialTheme.typography.titleMedium
        )

        // Text field for the working hours
        var workingHours by remember { mutableStateOf("") }

        CustomTextFieldMaxChar(
            descriptionState = mutableStateOf(workingHours),
            maxCharacters = 500,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Text
            )
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddLanguagePreview() {
    AddLanguage(rememberNavController())
}