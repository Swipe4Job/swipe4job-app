package cat.dam.grup2.swipe4job_app.features.candidate.state

import androidx.lifecycle.ViewModel
import cat.dam.grup2.swipe4job_app.features.candidate.model.CandidatePreferences

class AddPreferencesViewModel : ViewModel() {
    companion object { val instance = AddPreferencesViewModel() }

    var editingPreference: CandidatePreferences? = null
//    var editingIndex: Int = -1
}