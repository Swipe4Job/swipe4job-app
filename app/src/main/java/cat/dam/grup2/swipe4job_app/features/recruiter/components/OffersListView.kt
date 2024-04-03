package cat.dam.grup2.swipe4job_app.features.recruiter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cat.dam.grup2.swipe4job_app.R
import cat.dam.grup2.swipe4job_app.features.recruiter.models.JobOfferInformation
import cat.dam.grup2.swipe4job_app.features.recruiter.screens.itemToEdit
import cat.dam.grup2.swipe4job_app.features.recruiter.screens.itemToView
import cat.dam.grup2.swipe4job_app.features.recruiter.state.OfferListViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun OffersListView(
    navController: NavController,
    offerList: SnapshotStateList<JobOfferInformation>
) {
    val openDeleteDialog = remember { mutableStateOf(false) }
    var offerIndexToRemove by remember { mutableStateOf(0) }
    CustomAlertDialog(
        openDialog = openDeleteDialog,
        onAccept = {
            OfferListViewModel.instance.offerList.removeAt(offerIndexToRemove)
        },
        onDecline = {
            // Do nothing or handle cancellation if needed
        }
    )
    LazyColumn {
        items(OfferListViewModel.instance.offerList.size) { offerIndex ->
            val offer = OfferListViewModel.instance.offerList[offerIndex]

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
                        text = "${stringResource(R.string.jobType_text)}: ${offer.jobType.toStringResource(LocalContext.current)}",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Text(
                        text = "${stringResource(R.string.contractType_text)}: ${offer.contractType.toStringResource(LocalContext.current)}",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(top = 4.dp)
                    )
                    Text(
                        text = "${stringResource(R.string.workingDayType_text)}: ${offer.workingDayType.toStringResource(LocalContext.current)}",
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
                                onClick = {
                                    navController.navigate("candidateSimpleDetails")
                                }
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
                                onClick = {
                                    itemToView = offer
                                    navController.navigate("jobOfferRecruiterView")
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
                                    imageVector = Icons.Default.Visibility,
                                    contentDescription = stringResource(id = R.string.visibility_icon_description)
                                )
                            }
                            IconButton(
                                onClick = {
                                          itemToEdit = offer
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
                                    imageVector = Icons.Default.Edit,
                                    contentDescription = stringResource(id = R.string.edit_icon_description)
                                )
                            }
                            IconButton(
                                onClick = {
                                    offerIndexToRemove = offerIndex
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

                    }
                }
            }
        }
    }
}

@Composable
fun CustomAlertDialog(
    openDialog: MutableState<Boolean>,
    onAccept: () -> Unit,
    onDecline: () -> Unit
) {

    if (openDialog.value) {
        AlertDialog(
            onDismissRequest = { openDialog.value = false },
            text = {
                Text(text = stringResource(R.string.deleteJobOfferDialog_text))
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onAccept()
                        openDialog.value = false
                    }
                ) {
                    Text(
                        text = stringResource(R.string.deleteJobOfferDialog_AcceptText),
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDecline()
                        openDialog.value = false
                    }
                ) {
                    Text(
                        text = stringResource(R.string.deleteJobOfferDialog_DeclineText),
                    )
                }
            }
        )
    }
}

