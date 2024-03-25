package cat.dam.grup2.swipe4job_app.features.candidate

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import cat.dam.grup2.swipe4job_app.features.candidate.screens.JobExperience
import cat.dam.grup2.swipe4job_app.features.candidate.screens.LanguageSkill
import cat.dam.grup2.swipe4job_app.features.candidate.screens.Study


data class CandidateInformation(
    val description: String,
    val studies: List<Study>,
    val softskills: List<String>,
    val name: String,
    val lastname: String,
    val location: String,
    val languages: List<LanguageSkill>,
    val jobExperience: List<JobExperience>,
//    val preferences: List<Preference>
)