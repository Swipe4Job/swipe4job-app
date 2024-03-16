package cat.dam.grup2.swipe4job_app.features.candidate.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.sourceInformation
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import cat.dam.grup2.swipe4job_app.R
import cat.dam.grup2.swipe4job_app.features.candidate.CandidateInformation
import cat.dam.grup2.swipe4job_app.features.candidate.components.BottomNavigationBar
import cat.dam.grup2.swipe4job_app.features.candidate.components.BottomNavigationItem
import cat.dam.grup2.swipe4job_app.features.candidate.model.CandidatePreferences
import cat.dam.grup2.swipe4job_app.features.candidate.state.CandidateProfileViewModel

@Composable
fun CandidateCV(navController: NavController) {
    var selected by remember { mutableStateOf(BottomNavigationItem.CV) }
    val candidateProfileViewModel = CandidateProfileViewModel.getInstance()
    val softSkillsList = candidateProfileViewModel.softSkills
    val languagesList = candidateProfileViewModel.languages
    val studiesList = candidateProfileViewModel.studies
    val experiencesList = candidateProfileViewModel.experiences
    val preferences = candidateProfileViewModel.preferences

    val candidate = CandidateInformation(
        description = "",
        studies = listOf(),
        softskills = listOf(),
        name = "Paco",
        lastname = "Garcia",
        location = "",
        languages = listOf(),
        jobExperience = listOf()
    )
    val chipItems by remember { mutableStateOf(generateChips(softSkillsList)) }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                searchClick = { selected = BottomNavigationItem.SEARCH },
                connectionsClick = { selected = BottomNavigationItem.CONNECTIONS },
                cvClick = { selected = BottomNavigationItem.CV },
                notificationsClick = { selected = BottomNavigationItem.NOTIFICATIONS },
                selected = selected,
                navController = navController
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            item {
                Header(candidate = candidate)
                Experience(navController, experiencesList)
                Studies(navController, studiesList)
                SoftSkills(navController, softSkillsList, chipItems)
                Languages(navController, languagesList)
                Preferences(navController, preferences.value)
            }
        }
    }
}


@Composable
fun Header(candidate: CandidateInformation) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = stringResource(id = R.string.profile_image_description),
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "${candidate.name} ${candidate.lastname}",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun Experience(navController: NavController, experiencesList: List<JobExperience>) {
    ListField(
        title = R.string.candidate_jobExperience_title,
        emptyField = R.string.emptyExperience_text,
        onAddClick = {
            navController.navigate("addExperience")
        },
        itemsList = experiencesList,
    ) { Text(text = it.position + "\n" + it.company + "\n" + it.startDate + "\n" + it.endDate + "\n" + it.description) }
}

@Composable
fun Studies(navController: NavController, studiesList: List<Study>) {
    ListField(
        title = R.string.candidate_studies_title,
        emptyField = R.string.emptyStudies_text,
        onAddClick = {
            navController.navigate("addStudy")
        },
        itemsList = studiesList,
    ) {
        Text(
            text = it.name,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
        )
        Text(
            text = it.school,
            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold)
        )
        val dateRange = if (it.endDate != null) {
            "${it.startDate} - ${it.endDate}"
        } else {
            "${it.startDate} - Present"
        }
        Text(
            dateRange,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun SoftSkills(
    navController: NavController,
    softSkillsList: List<String>,
    chipItems: List<ChipItem>
) {
    ListField(
        title = R.string.candidate_softskills_title,
        emptyField = R.string.emptySoftskills_text,
        onAddClick = {
            navController.navigate("addSoftSkill")
        },
        itemsList = softSkillsList,
    ) {
        SuggestionChip(
            onClick = { /* Add your click action here */ },
            label = {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.onSurface
                )
            },
            border = SuggestionChipDefaults.suggestionChipBorder(
                enabled = true,
                borderColor = MaterialTheme.colorScheme.secondary,
                borderWidth = 2.dp,
            )
        )
    }
}

// Función para generar la lista de chips a partir de la lista de soft skills
fun generateChips(softSkillsList: List<String>): List<ChipItem> {
    return softSkillsList.distinct().map { ChipItem(label = it, icon = Icons.Default.Done) }
}


@Composable
fun Languages(navController: NavController, languagesList: List<LanguageSkill>) {
    ListField(
        title = R.string.candidate_languages_title,
        emptyField = R.string.emptyLanguages_text,
        onAddClick = {
            navController.navigate("addLanguage")
        },
        itemsList = languagesList,
    ) {
        Text(text = it.language + "\n" + it.level + "\n" + it.academicTitle)
    }
}

@Composable
fun Preferences(navController: NavController, preferences: CandidatePreferences?) {
    SingleField(
        title = R.string.candidate_preferences_title,
        onAddClick = {
            navController.navigate("addPreferences")
        },
    ) {
        if (preferences == null) {
            Text(stringResource(id = R.string.emptyPreferences_text))
            return@SingleField
        }

        Text(
            """
            ${preferences.salaryRange.toStringResource(LocalContext.current)}
            ${preferences.jobTypeOptions.toStringResource(LocalContext.current)}
            ${preferences.workingDayType.toStringResource(LocalContext.current)}
            ${preferences.contractTypeOptions.toStringResource(LocalContext.current)}
        """.trimIndent()
        )
    }
}

@Composable
fun SingleField(
    title: Int,
    onClick: () -> Unit = {},
    onAddClick: () -> Unit,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = title),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.secondary
            )
            OutlinedButton(
                onClick = onAddClick,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(vertical = 8.dp, horizontal = 2.dp),
                border = BorderStroke(2.dp, color = MaterialTheme.colorScheme.primary),
                content = {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = stringResource(id = R.string.add_icon_description),
                        )
                        Text(
                            text = stringResource(id = R.string.add_text_button),
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            )
        }
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    content()
                }
            }
        }
    }
}

@Composable
fun <T> ListField(
    title: Int,
    emptyField: Int,
    onAddClick: () -> Unit,
    onClick: (item: T) -> Unit = {},
    itemsList: List<T>,
    itemRenderer: @Composable (T) -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = title),
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                color = MaterialTheme.colorScheme.secondary
            )
            OutlinedButton(
                onClick = onAddClick,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(vertical = 8.dp, horizontal = 2.dp),
                border = BorderStroke(2.dp, color = MaterialTheme.colorScheme.primary),
                content = {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = stringResource(id = R.string.add_icon_description),
                        )
                        Text(
                            text = stringResource(id = R.string.add_text_button),
                            color = MaterialTheme.colorScheme.secondary
                        )
                    }
                }
            )
        }
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    if (itemsList.isEmpty()) {
                        Text(stringResource(id = emptyField))
                    } else {
                        itemsList.forEach {
                            Column(modifier = Modifier
                                .clickable {
                                    onClick(it)
                                }) {
                                itemRenderer(it)
                            }
                        }

                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    val candidate = CandidateInformation(
        description = "",
        studies = listOf(),
        softskills = listOf(),
        name = "Paco",
        lastname = "Garcia",
        location = "",
        languages = listOf(),
        jobExperience = listOf()
    )
    CandidateCV(rememberNavController())
}