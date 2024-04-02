package cat.dam.grup2.swipe4job_app.features.recruiter.state

import androidx.compose.runtime.mutableStateListOf
import cat.dam.grup2.swipe4job_app.features.recruiter.models.RecruiterConnection
import cat.dam.grup2.swipe4job_app.features.recruiter.screens.generateRecruiterConnectionsFakeData

data class RecruiterConnectionNotification (val connection: RecruiterConnection, var seen: Boolean)
class RecruiterConnectionsViewModel {
    val notifications = mutableStateListOf<RecruiterConnectionNotification>()

    companion object {
        var instance: RecruiterConnectionsViewModel? = null

        fun obtainInstance(): RecruiterConnectionsViewModel {
            if (instance == null) {
                instance = RecruiterConnectionsViewModel()

                instance!!.notifications.addAll(generateRecruiterConnectionsFakeData().map {
                    RecruiterConnectionNotification(it, false)
                })
            }
            return instance!!
        }
    }
}