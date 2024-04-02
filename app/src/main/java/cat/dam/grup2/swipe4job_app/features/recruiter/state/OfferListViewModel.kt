package cat.dam.grup2.swipe4job_app.features.recruiter.state

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import cat.dam.grup2.swipe4job_app.features.recruiter.models.JobOfferInformation
import cat.dam.grup2.swipe4job_app.features.recruiter.screens.generateFakeData

class OfferListViewModel : ViewModel() {
    companion object {
        val instance = OfferListViewModel()
    }
    val offerList = mutableStateListOf<JobOfferInformation>()

    init {
        offerList.addAll(generateFakeData())
    }
}