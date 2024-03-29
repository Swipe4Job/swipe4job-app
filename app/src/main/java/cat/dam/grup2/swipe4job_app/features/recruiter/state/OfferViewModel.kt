package cat.dam.grup2.swipe4job_app.features.recruiter.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import cat.dam.grup2.swipe4job_app.features.recruiter.models.OfferData
import cat.dam.grup2.swipe4job_app.features.recruiter.models.OfferPost
import cat.dam.grup2.swipe4job_app.features.users.user_api_service.model.UserData

class OfferViewModel : ViewModel() {
    var offerPost by mutableStateOf<OfferPost?>(null)
    var companyName by mutableStateOf<String>("")
    var contractType by mutableStateOf<String>("")
    var departmentOrganisation by mutableStateOf<String>("")
    var description by mutableStateOf<String>("")
    var jobType by mutableStateOf<String>("")
    var publicationDate by mutableStateOf<String>("")
    var recruiterId by mutableStateOf<String>("")
    var requirements by mutableStateOf<String>("")
    var responsibilities by mutableStateOf<String>("")
    var salaryRange by mutableStateOf<String>("")
    var skills by mutableStateOf<List<String>>(emptyList())
    var title by mutableStateOf<String>("")
    var workingDay by mutableStateOf<String>("")
    var workingHours by mutableStateOf<String>("")
    companion object {
        val instance: OfferViewModel = OfferViewModel()
    }
}