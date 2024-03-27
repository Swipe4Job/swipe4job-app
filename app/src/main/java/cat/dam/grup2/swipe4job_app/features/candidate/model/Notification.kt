package cat.dam.grup2.swipe4job_app.features.candidate.model

import java.util.Date

data class CandidateNotification(
    val notificationEvent: NotificationEvent,
    val notificationMessage: String,
    val notificationDate: Date
)

enum class NotificationEvent {
    LIKE_RECEIVED,
    MATCH_WITH_JOB_OFFER
}
