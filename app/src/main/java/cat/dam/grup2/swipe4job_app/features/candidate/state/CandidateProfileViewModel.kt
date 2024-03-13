package cat.dam.grup2.swipe4job_app.features.candidate.state

import androidx.lifecycle.ViewModel
import cat.dam.grup2.swipe4job_app.features.candidate.screens.LanguageSkill
import cat.dam.grup2.swipe4job_app.features.candidate.screens.Study

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

    var softSkills = mutableListOf<String>()
    var languages = mutableListOf<LanguageSkill>()
    var studies = mutableListOf<Study>()
}