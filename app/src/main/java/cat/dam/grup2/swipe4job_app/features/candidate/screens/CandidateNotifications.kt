package cat.dam.grup2.swipe4job_app.features.candidate.screens

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
import cat.dam.grup2.swipe4job_app.shared.retrofit.model.Notification
import cat.dam.grup2.swipe4job_app.shared.retrofit.model.NotificationEvent
import cat.dam.grup2.swipe4job_app.features.candidate.components.BottomNavigationItem
import cat.dam.grup2.swipe4job_app.features.candidate.components.CandidateBottomNavigationBar
import cat.dam.grup2.swipe4job_app.features.candidate.components.CandidateNotificationsView
import cat.dam.grup2.swipe4job_app.features.recruiter.screens.generateRandomDate

fun generateFakeCandidateNotifications(): List<Notification> {
    val notifications = mutableListOf<Notification>()

    val eventTypes = NotificationEvent.values()

    repeat(5) {
        val eventType = eventTypes.random()
        val message = getMessageForEventType(eventType)
        val date = generateRandomDate()

        notifications.add(Notification(eventType, null, message, date))
    }

    return notifications
}

fun getMessageForEventType(eventType: NotificationEvent): String {
    return when (eventType) {
        NotificationEvent.LIKE_RECEIVED -> "Liked your profile"
        NotificationEvent.JOB_OFFER_CONNECTION -> "Matched with job offer"
        NotificationEvent.CANDIDATE_CONNECTION -> "Matched with candidate"
    }
}

val candidateNotifications = generateFakeCandidateNotifications()

@Composable
fun CandidateNotifications(navController: NavController) {
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
            CandidateNotificationsView(
                notificationsList = candidateNotifications,
                onDeleteClick = { /* TODO  delete notification*/ }
            )
        }
    }
}
