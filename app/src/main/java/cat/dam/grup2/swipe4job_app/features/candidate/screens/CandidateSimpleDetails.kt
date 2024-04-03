package cat.dam.grup2.swipe4job_app.features.candidate.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import cat.dam.grup2.swipe4job_app.CustomError
import cat.dam.grup2.swipe4job_app.R
import cat.dam.grup2.swipe4job_app.features.candidate.CandidateInformation
import cat.dam.grup2.swipe4job_app.features.candidate.state.CandidateDetailsViewModel
import cat.dam.grup2.swipe4job_app.features.recruiter.components.RecruiterBottomNavigationBar
import cat.dam.grup2.swipe4job_app.features.recruiter.components.BottomNavigationItem
import cat.dam.grup2.swipe4job_app.shared.composables.MatchButtons
import cat.dam.grup2.swipe4job_app.ui.theme.AppTheme
import cat.dam.grup2.swipe4job_app.shared.composables.NewConnectionDialog
import com.alexstyl.swipeablecard.Direction
import com.alexstyl.swipeablecard.ExperimentalSwipeableCardApi
import com.alexstyl.swipeablecard.rememberSwipeableCardState
import com.alexstyl.swipeablecard.swipableCard
import kotlinx.coroutines.delay

@Composable
fun CandidateSimpleDetails(navController: NavController) {

    var selected by remember { mutableStateOf(BottomNavigationItem.SEARCH) }
    var connectionAnimation by remember { mutableStateOf(false) } // Flag per indicar si hi ha hagut connexió entre la oferta i el candidat
    val candidateDetailsViewModel = CandidateDetailsViewModel.getInstance()

    val likeHandler = { _: CandidateInformation ->
        candidateDetailsViewModel.goToNextCandidate()
        connectionAnimation = true
    }

    val dislikeHandler = { _: CandidateInformation ->
        candidateDetailsViewModel.goToNextCandidate()
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
                .padding(innerPadding)
                .fillMaxWidth(),
        ) {
            SimpleDetails(navController, onLike = likeHandler, onDislike = dislikeHandler)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
            ) {
                MatchButtons(
                    onDislikeClick = {
                        dislikeHandler(candidateDetailsViewModel.currentCandidate!!)
                    },
                    onLikeClick = {
                        likeHandler(candidateDetailsViewModel.currentCandidate!!)
                    }
                )
            }
            if (connectionAnimation) {
                NewConnectionDialog(onDismiss = { connectionAnimation = false }) {
                    delay(3000)
                    connectionAnimation = false
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalSwipeableCardApi::class)
@Composable
fun ColumnScope.SimpleDetails(
    navController: NavController,
    onLike: (candidate: CandidateInformation) -> Unit,
    onDislike: (candidate: CandidateInformation) -> Unit
) {
    val candidateViewModel = CandidateDetailsViewModel.getInstance()
    val candidate = candidateViewModel.currentCandidate

    if (candidate == null) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(align = Alignment.Center)
        ) {
            Text(stringResource(id = R.string.noMoreCandidatesToShow_text))
        }
        return
    }

    val skills = candidate.softskills
    val chipItems = skills.map { ChipItem(label = it, icon = Icons.Default.Done) }
    val state = rememberSwipeableCardState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .weight(1f)
            .padding(top = 10.dp)
            .swipableCard(
                state = state,
                onSwiped = {
                    val liked = it == Direction.Right
                    if (liked) {
                        onLike(candidate)
                    } else {
                        onDislike(candidate)
                    }
                },
                onSwipeCancel = {
                },
                blockedDirections = listOf(Direction.Up, Direction.Down)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        item {
            TextButton(
                onClick = {
                    navController.navigate("candidateComplexDetails")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.End)
                    .padding(end = 4.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.text_moreDetails),
                    textDecoration = TextDecoration.Underline
                )
            }
            Image(
                painter = painterResource(id = R.drawable.profile),
                contentDescription = stringResource(id = R.string.profile_image_description),
                modifier = Modifier
                    .size(250.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "${candidate.name} ${candidate.lastname}",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = stringResource(id = R.string.location_icon_description),
                    tint = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = candidate.location,
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            // Suggestion Chips
            FlowRow {
                chipItems.forEach { chipItem ->
                    SuggestionChip(
                        onClick = { /* Add your click action here */ },
                        label = {
                            Text(
                                chipItem.label,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        },
                        border = SuggestionChipDefaults.suggestionChipBorder(
                            enabled = true,
                            borderWidth = 2.dp,
                        ),
                        modifier = Modifier.padding(5.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CustomCandidateSimpleDetailsPreview() {
    AppTheme {
        CandidateSimpleDetails(rememberNavController())
    }
}

data class ChipItem(
    val label: String,
    val icon: ImageVector
)