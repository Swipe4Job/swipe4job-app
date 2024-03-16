package cat.dam.grup2.swipe4job_app.features.candidate.screens

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
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import cat.dam.grup2.swipe4job_app.shared.composables.MatchButtons
import cat.dam.grup2.swipe4job_app.ui.theme.AppTheme
import java.time.LocalDate
import cat.dam.grup2.swipe4job_app.R
import cat.dam.grup2.swipe4job_app.features.candidate.CandidateInformation
import cat.dam.grup2.swipe4job_app.shared.composables.IconVector
import cat.dam.grup2.swipe4job_app.shared.composables.NewConnectionDialog
import kotlinx.coroutines.delay


enum class LanguageLevel {
    Low,
    Intermediate,
    Advanced,
    Native
}

data class LanguageSkill(
    val language: String,
    val level: LanguageLevel,
    val academicTitle: String? = null,
)

data class JobExperience(
    val position: String,
    val company: String,
    val description: String?,
    val startDate: String,
    val endDate: String?
)

data class Study(
    val name: String,
    val school: String,
    val startDate: String,
    val endDate: String?
)

@Composable
fun SectionLabel(text: String, icon: IconVector? = null) {
    Row {
        if (icon != null) {
            when (icon) {
                is IconVector.ImageVectorIcon -> {
                    Icon(
                        imageVector = icon.imageVector,
                        contentDescription = "Heading icon",
                        modifier = Modifier.size(15.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
                is IconVector.PainterIcon -> {
                    Icon(
                        painter = icon.painter,
                        contentDescription = "Heading icon",
                        modifier = Modifier.size(15.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

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
fun Section(title: String, icon: IconVector? = null, content: @Composable () -> Unit) {
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
fun CandidateComplexDetails(navController: NavController) {
    var connectionAnimation by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(
                            onClick = {
                                navController.popBackStack() // O navega a la pantalla anterior según sea necesario
                            },
                            modifier = Modifier.padding(end = 4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = stringResource(id = R.string.back_icon_description)
                            )
                        }
                        Text(
                            text = "Paco Garcia",
                            style = MaterialTheme.typography.headlineMedium,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
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
                    CandidateInformation(
                        description = "Hello how are you",
                        jobExperience = listOf(
                            JobExperience(
                                position = "Full Stack developer",
                                company = "Telefonica",
                                description = "Hello how are you",
                                startDate = "2022-07",
                                endDate = "2023-06"
                            ),
                            JobExperience(
                                position = "Full Stack developer",
                                company = "Telefonica",
                                description = "Hello how are you",
                                startDate = "2022-07",
                                endDate = "2023-06"
                            )
                        ),
                        studies = listOf(
                            Study(
                                school = "INS Pla de l'Estany",
                                name = "DAM",
                                startDate = "2022-07",
                                endDate = "2023-06",
                            ),
                            Study(
                                school = "INS Pla de l'Estany",
                                name = "DAM",
                                startDate = "2022-07",
                                endDate = "2023-06",
                            )
                        ),
                        name = "Paco",
                        lastname = "Garcia",
                        location = "Barcelona",
                        softskills = listOf("Leadership", "Adaptability", "Negotiation"),
                        languages = listOf(
                            LanguageSkill(
                                language = "English",
                                level = LanguageLevel.Advanced,
                                academicTitle = "Oxford"
                            )
                        ),

                    )
                )
            }
            Box(
                modifier = Modifier
                    .height(IntrinsicSize.Min)
            ) {
                MatchButtons(
                    onDislikeClick = {},
                    onLikeClick = {
                        connectionAnimation = true
                    }
                )
            }
            if (connectionAnimation) {
                NewConnectionDialog(onDismiss = {connectionAnimation = false}) {
                    delay(3000)
                    connectionAnimation = false
                    navController.navigate("candidateSimpleDetails")
                }
            }
        }
    }
}

@Composable
fun UserInformationDisplay(information: CandidateInformation) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        item {
            Section(title = stringResource(id = R.string.candidate_description_title), icon = IconVector.ImageVectorIcon(Icons.Default.Description)) {
                Text(text = information.description,
                    style = MaterialTheme.typography.bodyMedium)
            }

            Section(title = stringResource(id = R.string.candidate_jobExperience_title), icon = IconVector.ImageVectorIcon(Icons.Default.Work)) {
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
                        "${it.startDate} - {${stringResource(id = R.string.present_text)}"
                    }
                    Text(dateRange,
                        style = MaterialTheme.typography.bodyMedium)
                    it.description?.let { description ->
                        Text(
                            description,
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Normal))
                    }
                    SectionDivider()
                }
            }

            Section(title = stringResource(id = R.string.candidate_studies_title), icon = IconVector.ImageVectorIcon(Icons.Default.School)) {
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

            Section(title = stringResource(id = R.string.candidate_softskills_title), icon = IconVector.ImageVectorIcon(Icons.Default.HistoryEdu)) {
                information.softskills.forEach {
                    SuggestionChip(onClick = { /*TODO*/ }, label = { Text(it) })
                }
            }

            Section(title = stringResource(id = R.string.candidate_languages_title), icon = IconVector.ImageVectorIcon(Icons.Default.Translate)) {
                information.languages.forEach {
                    Text(
                        it.language,
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
                    )
                    Text(it.level.name)
                    if (it.academicTitle != null) {
                        Text(
                            it.academicTitle,
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CustomCandidateComplexDetailsPreview() {
    AppTheme {
        CandidateComplexDetails(rememberNavController())
    }
}