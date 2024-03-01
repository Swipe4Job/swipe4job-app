package cat.dam.grup2.swipe4job_app.recruiter.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ListAlt
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cat.dam.grup2.swipe4job_app.R

@Composable
fun BottomNavigationBar(
    searchClick: () -> Unit,
    connectionsClick: () -> Unit,
    offersClick: () -> Unit,
    notificationsClick: () -> Unit,
    selected: BottomNavigationItem,
    navController: NavController,
    modifier: Modifier = Modifier
) {

    BottomAppBar {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            val searchIconColor =
                if (selected == BottomNavigationItem.SEARCH) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.secondary
            IconButton(onClick = {
                searchClick()
                navController.navigate("candidateSimpleDetails")
            }) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(id = R.string.search_icon_description),
                        tint = searchIconColor,
                        modifier = Modifier.size(25.dp)
                    )
                }
            }
            val connectionsIconColor =
                if (selected == BottomNavigationItem.CONNECTIONS) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.secondary
            IconButton(onClick = {
                connectionsClick()
                navController.navigate("candidateSimpleDetails")
            }) {
                Column( horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Favorite,
                        contentDescription = stringResource(id = R.string.connections_icon_description),
                        tint = connectionsIconColor,
                        modifier = Modifier.size(25.dp)
                    )
                }
            }
            val cvIconColor =
                if (selected == BottomNavigationItem.OFFERS) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.secondary
            IconButton(onClick = {
                offersClick()
                navController.navigate("offersList")
            }) {
                Column( horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.ListAlt,
                        contentDescription = stringResource(id = R.string.offers_icon_description),
                        tint = cvIconColor,
                        modifier = Modifier.size(25.dp)
                    )
                }
            }
            val notificationsIconColor =
                if (selected == BottomNavigationItem.NOTIFICATIONS) MaterialTheme.colorScheme.primary
                else MaterialTheme.colorScheme.secondary
            IconButton(onClick = {
                notificationsClick()
                navController.navigate("offersList")
            }) {
                Column( horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = stringResource(id = R.string.notifications_icon_description),
                        tint = notificationsIconColor,
                        modifier = Modifier.size(25.dp)
                    )
                }
            }
        }
    }
}

enum class BottomNavigationItem {
    SEARCH, CONNECTIONS, OFFERS, NOTIFICATIONS
}