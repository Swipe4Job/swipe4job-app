package cat.dam.grup2.swipe4job_app.features.candidate.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cat.dam.grup2.swipe4job_app.R
import cat.dam.grup2.swipe4job_app.features.candidate.model.CandidateConnection
import cat.dam.grup2.swipe4job_app.features.candidate.state.CandidateConnectionNotification
import cat.dam.grup2.swipe4job_app.features.candidate.state.CandidateConnectionsViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun CandidateConnectionsView(
    candidateConnectionsList: List<CandidateConnectionNotification>,
    onContactClick: (recruiter: CandidateConnection) -> Unit
) {
    LazyColumn {
        items(candidateConnectionsList.size) { index ->
            val notification = candidateConnectionsList[index]
            val connection = notification.connection
            val seen = notification.seen
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
                        text = "${stringResource(R.string.recruiter_text)}: ${connection.recruiterName} ${connection.recruiterLastName}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = if (seen) FontWeight.Normal else FontWeight.Bold
                    )
                    Text(
                        modifier = Modifier.padding(top = 4.dp),
                        text = "${stringResource(R.string.jobOffer_text)}: ${connection.jobOfferTitle}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = if (seen) FontWeight.Normal else FontWeight.Bold

                    )
                    val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    val formattedConnectionDate = dateFormatter.format(connection.connectionDate)
                    Text(
                        modifier = Modifier.padding(top = 4.dp),
                        text = "${stringResource(R.string.connectionDate_text)}: $formattedConnectionDate",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = if (seen) FontWeight.Normal else FontWeight.Bold
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        IconButton(
                            onClick = {
                                notification.seen = true
                                CandidateConnectionsViewModel.obtainInstance().notifications[index] = notification
                                onContactClick(connection)
                            },
                            modifier = Modifier
                                .background(
                                    color = Color.Transparent,
                                    shape = RoundedCornerShape(50)
                                )
                                .size(36.dp)
                        )
                        {
                            Icon(
                                imageVector = Icons.Default.Contacts,
                                contentDescription = stringResource(id = R.string.contact_icon_description)
                            )
                        }
                    }
                }
            }
        }
    }
}