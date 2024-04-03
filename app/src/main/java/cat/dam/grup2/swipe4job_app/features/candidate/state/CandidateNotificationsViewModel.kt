package cat.dam.grup2.swipe4job_app.features.candidate.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import cat.dam.grup2.swipe4job_app.features.candidate.screens.generateFakeCandidateNotifications
import cat.dam.grup2.swipe4job_app.shared.retrofit.model.Notification

data class CandidateNotificationNotification(val notification: Notification, var seen: Boolean)

class CandidateNotificationsViewModel {
    val notifications = mutableStateListOf<CandidateNotificationNotification>()

    companion object {
        var instance: CandidateNotificationsViewModel? = null

        @Composable
        fun obtainInstance(): CandidateNotificationsViewModel {
            if (instance == null) {
                instance = CandidateNotificationsViewModel()

                instance!!.notifications.addAll(
                    generateFakeCandidateNotifications().map {
                        CandidateNotificationNotification(it, false)
                    }
                )
            }
            return instance!!
        }
    }
}