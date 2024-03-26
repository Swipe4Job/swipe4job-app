package cat.dam.grup2.swipe4job_app.features.candidate.model

import java.util.Date

data class CandidateConnection(
    val recruiterName: String,
    val recruiterLastName: String,
    val recruiterPhone: String,
    val recruiterEmail: String,
    val jobOfferTitle: String,
    val connectionDate: Date
)