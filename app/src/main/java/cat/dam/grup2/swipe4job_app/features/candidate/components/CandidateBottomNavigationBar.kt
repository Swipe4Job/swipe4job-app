package cat.dam.grup2.swipe4job_app.features.candidate.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cat.dam.grup2.swipe4job_app.R

@Composable
fun CandidateBottomNavigationBar(
    searchClick: () -> Unit,
    connectionsClick: () -> Unit,
    cvClick: () -> Unit,
    notificationsClick: () -> Unit,
    selected: BottomNavigationItem,
    navController: NavController,
    modifier: Modifier = Modifier
) {

    BottomAppBar {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            CandidateBottomNavigationItem(
                icon = Icons.Default.Search,
                labelRes = R.string.search_text,
                contentDescriptionRes = R.string.search_icon_description,
                selected = selected == BottomNavigationItem.SEARCH,
                onClick = {
                    searchClick()
                    navController.navigate("jobOfferSimpleDetails")
                }
            )
            CandidateBottomNavigationItem(
                icon = Icons.Default.Favorite,
                labelRes = R.string.connections_text,
                contentDescriptionRes = R.string.connections_icon_description,
                selected = selected == BottomNavigationItem.CONNECTIONS,
                onClick = {
                    connectionsClick()
                    navController.navigate("candidateConnections")
                }
            )
            CandidateBottomNavigationItem(
                icon = Icons.Default.Description,
                labelRes = R.string.cv_text,
                contentDescriptionRes = R.string.cv_icon_description,
                selected = selected == BottomNavigationItem.CV,
                onClick = {
                    cvClick()
                    navController.navigate("candidateCV")
                }
            )
            CandidateBottomNavigationItem(
                icon = Icons.Default.Notifications,
                labelRes = R.string.notifications_text,
                contentDescriptionRes = R.string.notifications_icon_description,
                selected = selected == BottomNavigationItem.NOTIFICATIONS,
                onClick = {
                    notificationsClick()
                    navController.navigate("candidateCV")
                }
            )
        }
    }
}

@Composable
private fun CandidateBottomNavigationItem(
    icon: ImageVector,
    @StringRes labelRes: Int,
    @StringRes contentDescriptionRes: Int,
    selected: Boolean,
    onClick: () -> Unit
) {
    val label = stringResource(id = labelRes)
    val contentDescription = stringResource(id = contentDescriptionRes)
    val iconColor = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = iconColor,
                modifier = Modifier.size(25.dp)
            )
        }
        Text(
            text = label,
            color = iconColor,
            style = MaterialTheme.typography.bodySmall,
        )
    }
}

enum class BottomNavigationItem {
    SEARCH, CONNECTIONS, CV, NOTIFICATIONS
}