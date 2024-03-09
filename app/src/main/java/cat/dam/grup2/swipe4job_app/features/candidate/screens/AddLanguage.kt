package cat.dam.grup2.swipe4job_app.features.candidate.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Alignment

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun AddLanguage(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        IconButton(
                            onClick = {
                                navController.popBackStack()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = stringResource(id = R.string.back_icon_description),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                        Text(
                            text = stringResource(id = R.string.addLanguage_text),
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .weight(1f)
                        )
                        Text(
                            text = stringResource(id = R.string.save_text),
                            style = MaterialTheme.typography.titleSmall,
                            modifier = Modifier
                                .clickable {
                                    // TODO: Guardar les dades
                                }
                                .padding(end = 16.dp),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                AddLanguageContent()
            }
        }
    }
}

@Composable
fun AddLanguageContent() {
    var languageText = stringResource(id = R.string.language_text)
    var selectedLanguageItem by remember { mutableStateOf(languageText) }
    var languageOptions = stringArrayResource(R.array.languages_array).toList()

    var levelText = stringResource(id = R.string.level_text)
    var selectedLevelItem by remember { mutableStateOf(levelText) }
    var levelOptions = stringArrayResource(R.array.languages_level_array).toList()
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
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
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                stringResource(id = R.string.commentsAddLanguage_text),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            // Text field for the comments
            var comments by remember { mutableStateOf("") }

            CustomTextFieldMaxChar(
                descriptionState = mutableStateOf(comments),
                maxCharacters = 200,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done,
                    keyboardType = KeyboardType.Text
                )
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AddLanguagePreview() {
    AddLanguage(rememberNavController())
}