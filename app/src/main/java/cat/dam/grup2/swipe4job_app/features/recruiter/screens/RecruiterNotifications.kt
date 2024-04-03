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
import cat.dam.grup2.swipe4job_app.features.candidate.components.BottomNavigationItem
import cat.dam.grup2.swipe4job_app.features.candidate.components.CandidateBottomNavigationBar
import cat.dam.grup2.swipe4job_app.features.recruiter.components.RecruiterNotificationsView
import cat.dam.grup2.swipe4job_app.features.recruiter.screens.generateRandomDate
import cat.dam.grup2.swipe4job_app.features.recruiter.state.RecruiterNotificationsViewModel
import cat.dam.grup2.swipe4job_app.shared.retrofit.model.Notification
import cat.dam.grup2.swipe4job_app.shared.retrofit.model.NotificationEvent
import cat.dam.grup2.swipe4job_app.shared.retrofit.model.getMessageForEventType

@Composable
fun generateFakeRecruiterNotifications(): List<Notification> {
    val notifications = mutableListOf<Notification>()

    val filteredEventTypes = listOf(
        NotificationEvent.JOB_OFFER_LIKE_RECEIVED,
        NotificationEvent.CANDIDATE_CONNECTION
    )

    val eventTypes = NotificationEvent.values().filter { it in filteredEventTypes }

    repeat(5) {
        val eventType = eventTypes.random()
        val message = getMessageForEventType(eventType)
        val date = generateRandomDate()

        notifications.add(Notification(eventType, null, message, date))
    }

    return notifications
}

@Composable
fun RecruiterNotifications(navController: NavController) {
    val recruiterNotificationsList = RecruiterNotificationsViewModel.obtainInstance().notifications
    val recruiterNotificationsViewModel = RecruiterNotificationsViewModel.obtainInstance()
    var selected by remember { mutableStateOf(BottomNavigationItem.NOTIFICATIONS) }

    Scaffold(
        bottomBar = {
            CandidateBottomNavigationBar(
                searchClick = { selected = BottomNavigationItem.SEARCH },
                connectionsClick = { selected = BottomNavigationItem.CONNECTIONS },
                cvClick = { selected = BottomNavigationItem.CV },
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
                viewModel = recruiterNotificationsViewModel,
                onClick = {

                }
            )
        }
    }
}