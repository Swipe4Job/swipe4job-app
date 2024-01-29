package cat.dam.grup2.swipe4job_app.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.HistoryEdu
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Translate
import androidx.compose.material.icons.filled.Work
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cat.dam.grup2.swipe4job_app.composables.MatchButtons
import java.time.LocalDate


data class UserInformation(
    val description: String,
    val studies: List<Study>,
    val skills: List<String>,
    val name: String,
    val lastname: String,
    val location: String,
    val languages: List<LanguageSkill>,
    val jobExperience: List<JobExperience>
)

enum class LanguageLevel {
    Low,
    Intermediate,
    Advanced
}

data class LanguageSkill(
    val language: String,
    val level: LanguageLevel,
    val degree: String? = null,
)

data class JobExperience(
    val company: String,
    val startDate: LocalDate,
    val endDate: LocalDate?,
    val position: String
)

data class Study(
    val school: String,
    val startDate: LocalDate,
    val endDate: LocalDate?,
    val name: String
)

@Composable
fun SectionLabel(text: String, icon: ImageVector? = null) {
    Row {
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = "Heading icon",
                modifier = Modifier.size(15.dp),
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.width(5.dp))
        }
        Text(text = text,
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurfaceVariant)
    }
    Spacer(modifier = Modifier.height(5.dp))
}

@Composable
fun SectionDivider() {
    Spacer(modifier = Modifier.height(5.dp))
}

@Composable
fun Section(title: String, icon: ImageVector? = null, content: @Composable () -> Unit) {
    Card(
        modifier = Modifier.padding(5.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(5.dp)) {
            SectionLabel(text = title, icon = icon)
            Divider(color = MaterialTheme.colorScheme.onSurfaceVariant)
            content()
        }
    }
}


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CandidateComplexDetails() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Paco Garcia",
                        style = MaterialTheme.typography.headlineMedium
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer,
                )
            )
        },
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
                UserInformationDisplay(
                    information =
                    UserInformation(
                        description = "Hello how are you",
                        jobExperience = listOf(
                            JobExperience(
                                company = "Telefonica",
                                startDate = LocalDate.of(2022, 7, 12),
                                endDate = LocalDate.of(2023, 6, 20),
                                position = "Full Stack developer"
                            ),
                            JobExperience(
                                company = "Telefonica",
                                startDate = LocalDate.of(2022, 7, 12),
                                endDate = LocalDate.of(2023, 6, 20),
                                position = "Full Stack developer"
                            )
                        ),
                        studies = listOf(
                            Study(
                                school = "INS Pla de l'Estany",
                                name = "DAM",
                                startDate = LocalDate.of(2020, 9, 12),
                                endDate = LocalDate.of(2022, 6, 20)
                            ),
                            Study(
                                school = "INS Pla de l'Estany",
                                name = "DAM",
                                startDate = LocalDate.of(2020, 9, 12),
                                endDate = LocalDate.of(2022, 6, 20)
                            )
                        ),
                        name = "Paco",
                        lastname = "Garcia",
                        location = "Barcelona",
                        skills = listOf("Kotlin", "Android Development", "Web Development"),
                        languages = listOf(
                            LanguageSkill(
                                language = "English",
                                level = LanguageLevel.Advanced,
                                degree = "Oxford"
                            )
                        )
                    )
                )
            }
            Box(
                modifier = Modifier
                    .height(IntrinsicSize.Min)
            ) {
                MatchButtons()
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
fun UserInformationDisplay(information: UserInformation) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        item {
            Section(title = "Description", icon = Icons.Default.Description) {
                Text(text = information.description,
                    style = MaterialTheme.typography.bodyMedium)
            }

            Section(title = "Job Experience", icon = Icons.Default.Work) {
                information.jobExperience.forEach {
                    Text(
                        it.position,
                        style = MaterialTheme.typography.titleMedium                         .copy(fontWeight = FontWeight.SemiBold)
                    )
                    Text(it.company,
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold))
                    val dateRange = if (it.endDate != null) {
                        "${it.startDate} - ${it.endDate}"
                    } else {
                        "${it.startDate} - Present"
                    }
                    Text(dateRange,
                        style = MaterialTheme.typography.bodyMedium)
                    SectionDivider()
                }
            }

            Section(title = "Studies", icon = Icons.Default.School) {
                information.studies.forEach {
                    Text(
                        it.name,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
                    )
                    Text(it.school,
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold))
                    val dateRange = if (it.endDate != null) {
                        "${it.startDate} - ${it.endDate}"
                    } else {
                        "${it.startDate} - Present"
                    }
                    Text(dateRange,
                        style = MaterialTheme.typography.bodyMedium)
                    SectionDivider()
                }
            }

            Section(title = "Skills", icon = Icons.Default.HistoryEdu) {
                information.skills.forEach {
                    SuggestionChip(onClick = { /*TODO*/ }, label = { Text(it) })
                }
            }

            Section(title = "Languages", icon = Icons.Default.Translate) {
                information.languages.forEach {
                    Text(
                        it.language,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
                    )
                    Text(it.level.name)
                    if (it.degree != null) {
                        Text(
                            it.degree,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Light
                        )
                    }
                    SectionDivider()
                }
            }
        }
    }
}