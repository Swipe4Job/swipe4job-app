package cat.dam.grup2.swipe4job_app.features.candidate.state

import Criteria
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.dam.grup2.swipe4job_app.features.candidate.model.CandidatePreferences
import cat.dam.grup2.swipe4job_app.features.candidate.screens.JobExperience
import cat.dam.grup2.swipe4job_app.features.candidate.screens.LanguageSkill
import cat.dam.grup2.swipe4job_app.features.candidate.screens.Study
import cat.dam.grup2.swipe4job_app.features.users.state.UserViewModel
import cat.dam.grup2.swipe4job_app.userApiService
import filters.FilterGroup
import filters.Filters
import filters.filter.Filter
import filters.filter.Operators
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import orders.Orders

class CandidateProfileViewModel : ViewModel() {
    companion object {
        private var instance: CandidateProfileViewModel? = null
        fun getInstance(): CandidateProfileViewModel {
            if (instance == null) {
                instance = CandidateProfileViewModel()
            }
            return instance!!
        }
    }

    var imageURI = mutableStateOf<Uri?>(null)
    var softSkills = mutableListOf<String>()
    var languages = mutableListOf<LanguageSkill>()
    var studies = mutableListOf<Study>()
    var experiences = mutableListOf<JobExperience>()
    var preferences = mutableStateOf<CandidatePreferences?>(null)
    var fullName = mutableStateOf("Alejandro Marin")
    private var remoteJob: Job? = null

    fun fetchRemoteCVData() {
        this.remoteJob = this.viewModelScope.launch {
            val userViewModel = UserViewModel.getInstance()
            val userId = userViewModel.userData!!.id
            val candidates = userApiService.listCandidates(
                Criteria(
                    filters = Filters.create(
                        FilterGroup.create(
                            Filter.create("candidateId", Operators.EQUAL, userId)
                        )
                    ),
                    orders = Orders.EMPTY()
                )
            )

            if (candidates.isEmpty()) {
                return@launch
            }

            val candidate = candidates.first()
            fullName.value = "${candidate.name} ${candidate.lastname}"
            softSkills.clear()
            softSkills.addAll(candidate.softskills)
            languages.clear()
            languages.addAll(candidate.languages)
            studies.clear()
            studies.addAll(candidate.studies)
            experiences.clear()
            experiences.addAll(candidate.jobExperience)
        }
    }
}