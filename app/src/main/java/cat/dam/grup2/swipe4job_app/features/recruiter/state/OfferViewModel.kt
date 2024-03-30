package cat.dam.grup2.swipe4job_app.features.recruiter.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import cat.dam.grup2.swipe4job_app.features.recruiter.models.OfferData
import cat.dam.grup2.swipe4job_app.features.recruiter.models.OfferPost
import cat.dam.grup2.swipe4job_app.features.users.user_api_service.model.UserData

class OfferViewModel : ViewModel() {
    var offerPost by mutableStateOf<OfferPost?>(null)
    var companyName by mutableStateOf("")
    var contractType by mutableStateOf("")
    var departmentOrganisation by mutableStateOf("")
    var description by mutableStateOf("")
    var jobType by mutableStateOf("")
    var recruiterId by mutableStateOf("")
    var requirements by mutableStateOf("")
    var responsibilities by mutableStateOf("")
    var salaryRange by mutableStateOf("")
    var skills = mutableStateListOf<String>()
    var location by mutableStateOf("")
    var title by mutableStateOf("")
    var workingDay by mutableStateOf("")
    var workingHours by mutableStateOf("")
    companion object {
        val instance: OfferViewModel = OfferViewModel()
    }

    fun addSoftSkill(skill: String) {
        if (this.skills.contains(skill)) {
            return
        }

        skills.add(skill)
    }

    fun clear() {
        offerPost = null
        companyName = ""
        contractType = ""
        departmentOrganisation = ""
        description = ""
        jobType = ""
        recruiterId = ""
        requirements = ""
        responsibilities = ""
        salaryRange = ""
        skills.clear()
        location = ""
        title = ""
        workingDay = ""
        workingHours = ""
    }
}