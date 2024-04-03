package cat.dam.grup2.swipe4job_app.features.recruiter.state

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import cat.dam.grup2.swipe4job_app.shared.retrofit.model.Notification
import generateFakeRecruiterNotifications

data class RecruiterNotificationNotification(val notification: Notification, var seen: Boolean)

class RecruiterNotificationsViewModel {
    val notifications = mutableStateListOf<RecruiterNotificationNotification>()

    companion object {
        private var instance: RecruiterNotificationsViewModel? = null

//        @Composable
        fun obtainInstance(): RecruiterNotificationsViewModel {
            if (instance == null) {
                instance = RecruiterNotificationsViewModel()

                instance!!.notifications.addAll(
                    generateFakeRecruiterNotifications().map {
                        RecruiterNotificationNotification(it, false)
                    }
                )
            }
            return instance!!
        }
    }
}