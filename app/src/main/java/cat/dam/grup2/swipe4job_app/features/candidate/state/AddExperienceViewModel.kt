package cat.dam.grup2.swipe4job_app.features.candidate.state

import androidx.lifecycle.ViewModel
import cat.dam.grup2.swipe4job_app.features.candidate.screens.JobExperience

class AddExperienceViewModel : ViewModel() {
    companion object { val instance = AddExperienceViewModel() }

    var editingJobExperience: JobExperience? = null
    var editingIndex: Int = -1
}