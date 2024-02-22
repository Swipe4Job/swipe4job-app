package cat.dam.grup2.swipe4job_app.candidate.screens

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
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cat.dam.grup2.swipe4job_app.R
import cat.dam.grup2.swipe4job_app.candidate.CandidateInformation
import cat.dam.grup2.swipe4job_app.shared_composables.CustomButton
import cat.dam.grup2.swipe4job_app.shared_composables.IconVector

@Composable
fun CandidateCV() {
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

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            Header(candidate = candidate)
            Experience(candidate = candidate)
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
                text = stringResource(id = R.string.candidate_jobExperience_title),
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
                    Text(stringResource(id = R.string.emptyExperience_text))
                }
            }
        }
    }
}

@Composable
fun CustomOutlinedButtonWithText(
    onClick: () -> Unit,
    title: String
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier
            .wrapContentSize()
            .padding(8.dp),
        border = BorderStroke(2.dp, color = MaterialTheme.colorScheme.primary),
        content = {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.add_icon_description)
                )
                Text(
                    text = stringResource(id = buttonTextId),
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        }
    )
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
    Experience(candidate = candidate)
}
