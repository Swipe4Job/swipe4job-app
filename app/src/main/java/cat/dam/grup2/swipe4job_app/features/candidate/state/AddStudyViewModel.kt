package cat.dam.grup2.swipe4job_app.features.candidate.state

import androidx.lifecycle.ViewModel
import cat.dam.grup2.swipe4job_app.features.candidate.screens.Study

class AddStudyViewModel : ViewModel() {
    companion object { val instance = AddStudyViewModel() }

    var editingStudy: Study? = null
    var editingIndex: Int = -1
}