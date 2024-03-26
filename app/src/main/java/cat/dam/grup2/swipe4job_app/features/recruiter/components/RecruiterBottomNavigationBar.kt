package cat.dam.grup2.swipe4job_app.features.recruiter.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun RecruiterBottomNavigationBar(
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
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            RecruiterBottomNavigationItem(
                icon = Icons.Default.Search,
                label = "Search",
                selected = selected == BottomNavigationItem.SEARCH,
                onClick = {
                    searchClick()
                    navController.navigate("candidateSimpleDetails")
                }
            )
            RecruiterBottomNavigationItem(
                icon = Icons.Default.Favorite,
                label = "Connections",
                selected = selected == BottomNavigationItem.CONNECTIONS,
                onClick = {
                    connectionsClick()
                    navController.navigate("recruiterConnections")
                }
            )
            RecruiterBottomNavigationItem(
                icon = Icons.Default.ListAlt,
                label = "Offers",
                selected = selected == BottomNavigationItem.OFFERS,
                onClick = {
                    offersClick()
                    navController.navigate("offersList")
                }
            )
            RecruiterBottomNavigationItem(
                icon = Icons.Default.Notifications,
                label = "Notifications",
                selected = selected == BottomNavigationItem.NOTIFICATIONS,
                onClick = {
                    notificationsClick()
                    navController.navigate("offersList")
                }
            )
        }
    }
}

@Composable
private fun RecruiterBottomNavigationItem(
    icon: ImageVector,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    val iconColor = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = icon,
                contentDescription = label,
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
    SEARCH, CONNECTIONS, OFFERS, NOTIFICATIONS
}