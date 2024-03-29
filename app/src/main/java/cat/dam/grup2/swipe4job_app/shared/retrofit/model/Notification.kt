package cat.dam.grup2.swipe4job_app.shared.retrofit.model

import java.util.Date

data class Notification(
    val notificationEvent: NotificationEvent,
    val notificationData: Any?,
    val notificationMessage: String,
    val notificationDate: Date
)

enum class NotificationEvent {
    LIKE_RECEIVED,
    JOB_OFFER_CONNECTION,
    CANDIDATE_CONNECTION
}
