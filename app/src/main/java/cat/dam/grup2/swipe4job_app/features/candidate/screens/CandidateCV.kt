package cat.dam.grup2.swipe4job_app.features.candidate.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Place
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CandidateCV(navController: NavController) {
    var selected by remember { mutableStateOf(BottomNavigationItem.CV) }

    val candidate = CandidateInformation(
        description = "",
        studies = listOf(),
        skills = listOf(),
        name = "",
        lastname = "",
        location = "",
        languages = listOf(),
        jobExperience = listOf()
    )

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
                Experience(candidate = candidate)
                Studies(candidate = candidate)
                Skills(candidate = candidate)
                Languages(candidate = candidate)
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
                text = "${candidate.name} ${candidate.lastname}"
            )
        }
    }
}

@Composable
fun Experience(candidate: CandidateInformation) {
    EmptyField(
        candidate = candidate,
        title = R.string.candidate_jobExperience_title,
        emptyField = R.string.emptyExperience_text
    )
}

@Composable
fun Studies(candidate: CandidateInformation) {
    EmptyField(
        candidate = candidate,
        title = R.string.candidate_studies_title,
        emptyField = R.string.emptyStudies_text
    )
}

@Composable
fun Skills(candidate: CandidateInformation) {
    EmptyField(
        candidate = candidate,
        title = R.string.candidate_skills_title,
        emptyField = R.string.emptySkills_text
    )
}

@Composable
fun Languages(candidate: CandidateInformation) {
    EmptyField(
        candidate = candidate,
        title = R.string.candidate_languages_title,
        emptyField = R.string.emptyLanguages_text
    )
}

@Composable
fun EmptyField(
    candidate: CandidateInformation,
    title: Int,
    emptyField: Int
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
                onClick = {
                    /*TODO*/
                },
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
                    Text(stringResource(id = emptyField))
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
        skills = listOf(),
        name = "Janira",
        lastname = "Huesca",
        location = "",
        languages = listOf(),
        jobExperience = listOf()
    )
    CandidateCV(rememberNavController())
}