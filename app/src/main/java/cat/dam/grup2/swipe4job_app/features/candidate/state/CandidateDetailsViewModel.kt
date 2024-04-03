package cat.dam.grup2.swipe4job_app.features.candidate.state

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import cat.dam.grup2.swipe4job_app.features.candidate.CandidateInformation
import cat.dam.grup2.swipe4job_app.features.candidate.screens.JobExperience
import cat.dam.grup2.swipe4job_app.features.candidate.screens.LanguageLevel
import cat.dam.grup2.swipe4job_app.features.candidate.screens.LanguageSkill
import cat.dam.grup2.swipe4job_app.features.candidate.screens.Study

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

                repeat(3) {
                    instance!!.candidates.add(CandidateInformation(
                        description = "Hello how are you",
                        jobExperience = listOf(
                            JobExperience(
                                position = "Full Stack developer",
                                company = "Telefonica",
                                description = "Hello how are you",
                                startDate = "2022-07",
                                endDate = "2023-06"
                            ),
                            JobExperience(
                                position = "Full Stack developer",
                                company = "Telefonica",
                                description = "Hello how are you",
                                startDate = "2022-07",
                                endDate = "2023-06"
                            )
                        ),
                        studies = listOf(
                            Study(
                                school = "INS Pla de l'Estany",
                                name = "DAM",
                                startDate = "2022-07",
                                endDate = "2023-06",
                            ),
                            Study(
                                school = "INS Pla de l'Estany",
                                name = "DAM",
                                startDate = "2022-07",
                                endDate = "2023-06",
                            )
                        ),
                        name = "Paco",
                        lastname = "Garcia",
                        location = "Barcelona",
                        softskills = listOf("Leadership", "Adaptability", "Negotiation"),
                        languages = listOf(
                            LanguageSkill(
                                language = "English",
                                level = LanguageLevel.Advanced,
                                academicTitle = "Oxford"
                            )
                        ),
                    ))
                }
                instance!!.currentCandidate = instance!!.candidates[0]
            }

            return instance!!
        }
    }
}
