package cat.dam.grup2.swipe4job_app.features.candidate.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import cat.dam.grup2.swipe4job_app.R
import cat.dam.grup2.swipe4job_app.features.candidate.state.CandidateNotificationNotification
import cat.dam.grup2.swipe4job_app.features.candidate.state.CandidateNotificationsViewModel
import cat.dam.grup2.swipe4job_app.shared.retrofit.model.Notification
import cat.dam.grup2.swipe4job_app.shared.retrofit.model.getMessageForEventType
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun CandidateNotificationsView(
    notificationsList: List<CandidateNotificationNotification>,
    onClick: (notification: CandidateNotificationNotification, index: Int) -> Unit
) {
    LazyColumn {
        items(notificationsList.size) { index ->
            val notificationItem = notificationsList[index]
            val notification = notificationsList[index].notification
            var seen = notificationsList[index].seen
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = RoundedCornerShape(8.dp),
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                    Text(
                        text = getMessageForEventType(LocalContext.current, notification.notificationEvent),
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = if (seen) FontWeight.Normal else FontWeight.Bold
                    )
                    val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    val formattedConnectionDate = dateFormatter.format(notification.notificationDate)
                    Text(
                        modifier = Modifier.padding(top = 4.dp),
                        text = "${stringResource(R.string.notificationDate_text)}: $formattedConnectionDate",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = if (seen) FontWeight.Normal else FontWeight.Bold
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(
                            onClick = { seen = true
                                onClick(notificationItem, index)
                            }
                        ) {
                            Text(
                                text = stringResource(id = R.string.markAsRead_text),
                                color = MaterialTheme.colorScheme.primary,
                                fontWeight = FontWeight.Bold,
                                textDecoration = TextDecoration.Underline
                            )
                        }
                    }
                }
            }
        }
    }
}