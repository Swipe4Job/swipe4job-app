package cat.dam.grup2.swipe4job_app.features.recruiter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import cat.dam.grup2.swipe4job_app.R
import cat.dam.grup2.swipe4job_app.features.recruiter.models.JobOfferInformation
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun OffersListView(
    offerList: MutableList<JobOfferInformation>,
    onSearchClick: () -> Unit,
    onViewClick: (offer: JobOfferInformation) -> Unit,
    onEditClick: (offer: JobOfferInformation) -> Unit,
    onDeleteClick: (offer: JobOfferInformation) -> Unit
) {
    val openDeleteDialog = remember { mutableStateOf(false) }
    var selectedOfferToDelete: JobOfferInformation? by remember { mutableStateOf(null) }

    LazyColumn {
        items(offerList) { offer ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text(
                        text = "${stringResource(R.string.jobTitle_text)}: ${offer.jobTitle}",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "${stringResource(R.string.location_text)}: ${offer.location}",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Text(
                        text = "${stringResource(R.string.jobType_text)}: ${offer.jobType}",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Text(
                        text = "${stringResource(R.string.contractType_text)}: ${offer.contractType}",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Text(
                        text = "${stringResource(R.string.workingDayType_text)}: ${offer.workingDayType}",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 4.dp)
                    )

                    val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                    val formattedPublicationDate = dateFormatter.format(offer.publicationDate)
                    Text(
                        text = "${stringResource(R.string.publicationDate_text)}: $formattedPublicationDate",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 4.dp)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(modifier = Modifier.weight(1f)) {
                            TextButton(
                                onClick = { onSearchClick() }
                            ) {
                                Text(
                                    text = stringResource(id = R.string.searchCandidates_text),
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Bold,
                                    textDecoration = TextDecoration.Underline
                                )
                            }
                        }
                        Row(
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(
                                onClick = { onViewClick(offer) },
                                modifier = Modifier
                                    .background(
                                        color = Color.Transparent,
                                        shape = RoundedCornerShape(50)
                                    )
                                    .size(36.dp)
                            )
                            {
                                Icon(
                                    imageVector = Icons.Default.Visibility,
                                    contentDescription = stringResource(id = R.string.visibility_icon_description)
                                )
                            }
                            IconButton(
                                onClick = { onEditClick(offer) },
                                modifier = Modifier
                                    .background(
                                        color = Color.Transparent,
                                        shape = RoundedCornerShape(50)
                                    )
                                    .size(36.dp)
                                    .padding(start = 8.dp)
                            )
                            {
                                Icon(
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = stringResource(id = R.string.edit_icon_description)
                                )
                            }
                            IconButton(
                                onClick = {
                                    onDeleteClick(offer)
                                    openDeleteDialog.value = true
                                },
                                modifier = Modifier
                                    .background(
                                        color = Color.Transparent,
                                        shape = RoundedCornerShape(50)
                                    )
                                    .size(36.dp)
                                    .padding(start = 8.dp)
                            )
                            {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = stringResource(id = R.string.delete_icon_description)
                                )
                            }
                        }
                        CustomAlertDialog(
                            openDialog = openDeleteDialog,
                            onAccept = {
                                selectedOfferToDelete?.let { offerToDelete ->
                                    offerList.remove(offerToDelete)
                                    onDeleteClick(offerToDelete)
                                }
                            },
                            onDecline = {
                                // Do nothing or handle cancellation if needed
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CustomAlertDialog(
    openDialog: MutableState<Boolean>,
    onAccept: (String) -> Unit,
    onDecline: () -> Unit
) {

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            text = {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = stringResource(R.string.deleteJobOfferDialog_text))
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        TextButton(
                            onClick = {
                                openDialog.value = false
                                val selectedOption = "Deleted"
                                onAccept(selectedOption)
                            }
                        ) {
                            Text(
                                text = stringResource(R.string.deleteJobOfferDialog_AcceptText),
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        TextButton(
                            onClick = {
                                openDialog.value = false
                                onDecline()
                            }
                        ) {
                            Text(
                                text = stringResource(R.string.deleteJobOfferDialog_DeclineText),
                            )
                        }
                    }
                }
            },
            confirmButton = { },
            dismissButton = { }
        )
    }
}

