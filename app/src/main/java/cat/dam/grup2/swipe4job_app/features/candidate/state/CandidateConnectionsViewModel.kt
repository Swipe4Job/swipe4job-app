package cat.dam.grup2.swipe4job_app.features.candidate.state

import androidx.compose.runtime.mutableStateListOf
import cat.dam.grup2.swipe4job_app.features.candidate.model.CandidateConnection
import cat.dam.grup2.swipe4job_app.features.candidate.screens.generateCandidateConnectionsFakeData

data class CandidateConnectionNotification (val connection: CandidateConnection, var seen: Boolean)

class CandidateConnectionsViewModel {
    val notifications = mutableStateListOf<CandidateConnectionNotification>()

    companion object {
        var instance: CandidateConnectionsViewModel? = null

        fun obtainInstance(): CandidateConnectionsViewModel {
            if (instance == null) {
                instance = CandidateConnectionsViewModel()

                instance!!.notifications.addAll(generateCandidateConnectionsFakeData().map {
                    CandidateConnectionNotification(it, false)
                })
            }
            return instance!!
        }
    }
}