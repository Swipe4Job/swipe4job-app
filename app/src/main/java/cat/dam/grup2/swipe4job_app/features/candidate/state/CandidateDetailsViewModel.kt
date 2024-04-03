package cat.dam.grup2.swipe4job_app.features.candidate.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.dam.grup2.swipe4job_app.features.candidate.CandidateInformation
import cat.dam.grup2.swipe4job_app.features.candidate.screens.JobExperience
import cat.dam.grup2.swipe4job_app.features.candidate.screens.LanguageLevel
import cat.dam.grup2.swipe4job_app.features.candidate.screens.LanguageSkill
import cat.dam.grup2.swipe4job_app.features.candidate.screens.Study
import cat.dam.grup2.swipe4job_app.userApiService
import kotlinx.coroutines.launch

class CandidateDetailsViewModel : ViewModel() {
    val candidates = mutableStateListOf<CandidateInformation>()
    private var currentCandidateIndex: Int by mutableStateOf(0)
    var currentCandidate by mutableStateOf<CandidateInformation?>(null)

    fun goToNextCandidate() {
        currentCandidate = null
        currentCandidateIndex++
        if (currentCandidateIndex >= candidates.size) {
            return
        }
        currentCandidate = candidates[currentCandidateIndex]
    }

    companion object {
        private var instance: CandidateDetailsViewModel? = null

        fun getInstance(): CandidateDetailsViewModel {
            if (instance == null) {
                val candidateViewModel = CandidateDetailsViewModel()
                instance = candidateViewModel


                instance!!.viewModelScope.launch {
                    val candidates = userApiService.listCandidates(Criteria.NONE())
                    instance!!.candidates.addAll(candidates)
                    instance!!.currentCandidate = instance!!.candidates[0]
                }
            }

            return instance!!
        }
    }
}
