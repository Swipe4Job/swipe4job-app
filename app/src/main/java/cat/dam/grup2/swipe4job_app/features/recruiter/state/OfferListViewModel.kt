package cat.dam.grup2.swipe4job_app.features.recruiter.state

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.dam.grup2.swipe4job_app.CustomError
import cat.dam.grup2.swipe4job_app.features.recruiter.models.ContractTypeOptions
import cat.dam.grup2.swipe4job_app.features.recruiter.models.JobOfferInformation
import cat.dam.grup2.swipe4job_app.features.recruiter.models.JobTypeOptions
import cat.dam.grup2.swipe4job_app.features.recruiter.models.SalaryRange
import cat.dam.grup2.swipe4job_app.features.recruiter.models.WorkingDayTypeOptions
import cat.dam.grup2.swipe4job_app.userApiService
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class OfferListViewModel : ViewModel() {
    companion object {
        val instance = OfferListViewModel()
    }
    var offerList = mutableStateListOf<JobOfferInformation>()

    init {
        viewModelScope.launch {
            val offers = userApiService.listOffers(Criteria.NONE())
            offerList.addAll(offers)
        }
    }

    suspend fun refreshOffers() {
        val offers = userApiService.listOffers(Criteria.NONE())
        offerList.clear()
        offerList.addAll(offers)
    }
}