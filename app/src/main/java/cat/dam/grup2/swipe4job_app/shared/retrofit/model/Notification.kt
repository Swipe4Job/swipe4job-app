package cat.dam.grup2.swipe4job_app.shared.retrofit.model

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import java.util.Date
import cat.dam.grup2.swipe4job_app.R

data class Notification(
    val notificationEvent: NotificationEvent,
    val notificationData: Any?,
    val notificationMessage: String,
    val notificationDate: Date
)

enum class NotificationEvent {
    JOB_OFFER_LIKE_RECEIVED,
    PROFILE_LIKE_RECEIVED,
    JOB_OFFER_CONNECTION,
    CANDIDATE_CONNECTION
}

@Composable
fun getMessageForEventType(eventType: NotificationEvent): String {
    return when (eventType) {
        NotificationEvent.JOB_OFFER_LIKE_RECEIVED -> stringResource(id = R.string.jobOfferLikeReceived_text)
        NotificationEvent.PROFILE_LIKE_RECEIVED -> stringResource(id = R.string.profileLikeReceived_text)
        NotificationEvent.JOB_OFFER_CONNECTION -> stringResource(id = R.string.jobOfferConnection_text)
        NotificationEvent.CANDIDATE_CONNECTION -> stringResource(id = R.string.candidateConnection_text)
    }
}
