package cat.dam.grup2.swipe4job_app.features.candidate.screens

import android.content.Context
import androidx.compose.foundation.background
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import cat.dam.grup2.swipe4job_app.shared.composables.MatchButtons
import cat.dam.grup2.swipe4job_app.ui.theme.AppTheme
import cat.dam.grup2.swipe4job_app.R
import cat.dam.grup2.swipe4job_app.features.candidate.CandidateInformation
import cat.dam.grup2.swipe4job_app.features.candidate.state.CandidateDetailsViewModel
import cat.dam.grup2.swipe4job_app.features.recruiter.components.RecruiterBottomNavigationBar
import cat.dam.grup2.swipe4job_app.features.recruiter.components.BottomNavigationItem
import cat.dam.grup2.swipe4job_app.features.recruiter.models.LanguageList
import cat.dam.grup2.swipe4job_app.features.recruiter.models.SoftSkillsList
import cat.dam.grup2.swipe4job_app.shared.composables.IconVector
import cat.dam.grup2.swipe4job_app.shared.composables.NewConnectionDialog
import kotlinx.coroutines.delay
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale


enum class LanguageLevel {
    LOW,
    INTERMEDIATE,
    ADVANCED,
    NATIVE;

    companion object {
        fun fromResourceString(context: Context, resourceString: String): LanguageLevel {
            val languageLevelResourceList =
                context.resources.getStringArray(R.array.languages_level_array).toList()

            return when (resourceString) {
                languageLevelResourceList[0] -> LOW
                languageLevelResourceList[1] -> INTERMEDIATE
                languageLevelResourceList[2] -> ADVANCED
                languageLevelResourceList[3] -> NATIVE
                else -> throw Error("Unexpected language level resource string $resourceString")
            }
        }
    }

    fun toResourceString(context: Context): String {
        val languageLevelResourceList =
            context.resources.getStringArray(R.array.languages_level_array).toList()

        return when (this) {
            LOW -> languageLevelResourceList[0]
            INTERMEDIATE -> languageLevelResourceList[1]
            ADVANCED -> languageLevelResourceList[2]
            NATIVE -> languageLevelResourceList[3]
        }
    }
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
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
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
fun CandidateComplexDetails(navController: NavController) {
    val candidateViewModel = CandidateDetailsViewModel.getInstance()
    var selected by remember { mutableStateOf(BottomNavigationItem.SEARCH) }
    var connectionAnimation by remember { mutableStateOf(false) }
    val currentCandidate = candidateViewModel.currentCandidate
    if (currentCandidate == null) {
        return
    }

    Scaffold(
        bottomBar = {
            RecruiterBottomNavigationBar(
                searchClick = { selected = BottomNavigationItem.SEARCH },
                connectionsClick = { selected = BottomNavigationItem.CONNECTIONS },
                offersClick = { selected = BottomNavigationItem.OFFERS },
                notificationsClick = { selected = BottomNavigationItem.NOTIFICATIONS },
                selected = selected,
                navController = navController
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
                UserInformationDisplay(
                    navController,
                    information = currentCandidate
                )
            }
            Box(
                modifier = Modifier
                    .height(IntrinsicSize.Min)
            ) {
                MatchButtons(
                    onDislikeClick = {
                        candidateViewModel.goToNextCandidate()
                    },
                    onLikeClick = {
                        connectionAnimation = true
                    }
                )
            }
            if (connectionAnimation) {
                NewConnectionDialog(onDismiss = { connectionAnimation = false }) {
                    delay(3000)
                    connectionAnimation = false
                    navController.navigate("candidateSimpleDetails")
                    candidateViewModel.goToNextCandidate()
                }
            }
        }
    }
}

@Composable
fun UserInformationDisplay(navController: NavController, information: CandidateInformation) {
    val currentCandidate = CandidateDetailsViewModel.getInstance().currentCandidate

    Column(
        modifier = Modifier
            .fillMaxSize()
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.colorScheme.primaryContainer),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier.padding(end = 4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.back_icon_description)
                )
            }
            Text(
                text = "${currentCandidate!!.name} ${currentCandidate!!.lastname}",
                modifier = Modifier.padding(start = 4.dp),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onPrimaryContainer
            )
        }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        ) {
            item {
                Section(
                    title = stringResource(id = R.string.candidate_description_title),
                    icon = IconVector.ImageVectorIcon(Icons.Default.Description)
                ) {
                    Text(
                        text = information.description,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

                Section(
                    title = stringResource(id = R.string.candidate_jobExperience_title),
                    icon = IconVector.ImageVectorIcon(Icons.Default.Work)
                ) {
                    information.jobExperience.forEach {
                        Text(
                            it.position,
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
                        )
                        Text(
                            it.company,
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold)
                        )
                        val dateRange = if (it.endDate != null) {
                            "${formatDate(it.startDate)} - ${formatDate(it.endDate)}"
                        } else {
                            "${formatDate(it.startDate)} - ${stringResource(id = R.string.present_text)}"
                        }
                        Text(
                            dateRange,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        it.description?.let { description ->
                            Text(
                                description,
                                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.Normal)
                            )
                        }
                        SectionDivider()
                    }
                }

                Section(
                    title = stringResource(id = R.string.candidate_studies_title),
                    icon = IconVector.ImageVectorIcon(Icons.Default.School)
                ) {
                    information.studies.forEach {
                        Text(
                            it.name,
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
                        )
                        Text(
                            it.school,
                            style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold)
                        )
                        val dateRange = if (it.endDate != null) {
                            "${formatDate(it.startDate)} - ${formatDate(it.endDate)}"
                        } else {
                            "${formatDate(it.startDate)} - ${stringResource(id = R.string.present_text)}"
                        }
                        Text(
                            dateRange,
                            style = MaterialTheme.typography.bodyMedium
                        )
                        SectionDivider()
                    }
                }

                Section(
                    title = stringResource(id = R.string.candidate_softskills_title),
                    icon = IconVector.ImageVectorIcon(Icons.Default.HistoryEdu)
                ) {
                    information.softskills.forEach {
                        val softSkill = SoftSkillsList.toResourceString(LocalContext.current, it)
                        SuggestionChip(onClick = { /*TODO*/ }, label = { Text(softSkill) })
                    }
                }

                Section(
                    title = stringResource(id = R.string.candidate_languages_title),
                    icon = IconVector.ImageVectorIcon(Icons.Default.Translate)
                ) {
                    information.languages.forEach {
                        Text(
                            LanguageList.toResourceString(LocalContext.current, it.language),
                            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
                        )
                        Text(it.level.toResourceString(LocalContext.current))
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
}

fun formatDate(date: String): String {
    val format = DateTimeFormatter.ISO_INSTANT
    val date = Date.from(Instant.from(format.parse(date)))
    val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    return dateFormatter.format(date)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CustomCandidateComplexDetailsPreview() {
    AppTheme {
        CandidateComplexDetails(rememberNavController())
    }
}