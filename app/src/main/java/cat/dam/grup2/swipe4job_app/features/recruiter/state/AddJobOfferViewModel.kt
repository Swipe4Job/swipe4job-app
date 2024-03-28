package cat.dam.grup2.swipe4job_app.features.recruiter.state

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class AddJobOfferViewModel : ViewModel() {
    companion object {
        val instance = AddJobOfferViewModel()
    }

    val softSkills = mutableStateListOf<String>()

    fun addSoftSkill(skill: String) {
        if (this.softSkills.contains(skill)) {
            return
        }

        softSkills.add(skill)
    }
}