import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import cat.dam.grup2.swipe4job_app.features.recruiter.components.BottomNavigationItem
import cat.dam.grup2.swipe4job_app.features.recruiter.components.RecruiterBottomNavigationBar
import cat.dam.grup2.swipe4job_app.features.recruiter.components.RecruiterNotificationsView
import cat.dam.grup2.swipe4job_app.features.recruiter.screens.generateRandomDate
import cat.dam.grup2.swipe4job_app.features.recruiter.state.RecruiterNotificationsViewModel
import cat.dam.grup2.swipe4job_app.shared.retrofit.model.Notification
import cat.dam.grup2.swipe4job_app.shared.retrofit.model.NotificationEvent

fun generateFakeRecruiterNotifications(): List<Notification> {
    val notifications = mutableListOf<Notification>()

    val filteredEventTypes = listOf(
        NotificationEvent.JOB_OFFER_LIKE_RECEIVED,
        NotificationEvent.CANDIDATE_CONNECTION
    )

    val eventTypes = NotificationEvent.values().filter { it in filteredEventTypes }

    val jobTitles = listOf(
        "Programador Java",
        "Programador/a .NET junior",
        "Programador Web"
    )

    repeat(3) {
        val eventType = eventTypes.random()
        val date = generateRandomDate()
        val jobTitle = jobTitles[it]

        notifications.add(Notification(
            eventType, null, date, jobTitle))
    }

    return notifications
}

@Composable
fun RecruiterNotifications(navController: NavController) {
    val recruiterNotificationsList = RecruiterNotificationsViewModel.obtainInstance().notifications
    var selected by remember { mutableStateOf(BottomNavigationItem.NOTIFICATIONS) }

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
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            RecruiterNotificationsView(
                notificationsList = recruiterNotificationsList,
                onClick = { notificationItem, index ->
                    notificationItem.seen = true
                    recruiterNotificationsList[index] = notificationItem
                    navController.navigate("recruiterNotifications")
                }
            )
        }
    }
}