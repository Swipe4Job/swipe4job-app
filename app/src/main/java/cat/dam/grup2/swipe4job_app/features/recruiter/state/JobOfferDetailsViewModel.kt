package cat.dam.grup2.swipe4job_app.features.recruiter.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.dam.grup2.swipe4job_app.features.recruiter.models.JobOfferInformation
import cat.dam.grup2.swipe4job_app.userApiService
import kotlinx.coroutines.launch

class JobOfferDetailsViewModel : ViewModel() {
    val jobOffers = mutableListOf<JobOfferInformation>()
    private var currentJobOfferIndex: Int by mutableStateOf(0)
    var currentJobOffer by mutableStateOf<JobOfferInformation?>(null)

    fun goToNextJobOffer() {
        currentJobOfferIndex++
        if (currentJobOfferIndex >= jobOffers.size) {
            currentJobOffer = null
            return
        }
        currentJobOffer = jobOffers[currentJobOfferIndex]
    }

    companion object {
        private var instance: JobOfferDetailsViewModel? = null

        fun getInstance(): JobOfferDetailsViewModel {
            if (instance == null) {
                val jobOfferDetailsViewModel = JobOfferDetailsViewModel()
                instance = jobOfferDetailsViewModel

                instance!!.viewModelScope.launch {
                    var offers = userApiService.listOffers(Criteria.NONE())
                    instance!!.jobOffers.addAll(
                        offers,
                    )
                    instance!!.currentJobOffer = instance!!.jobOffers[0]
                }
            }
            return instance!!
        }
    }
}