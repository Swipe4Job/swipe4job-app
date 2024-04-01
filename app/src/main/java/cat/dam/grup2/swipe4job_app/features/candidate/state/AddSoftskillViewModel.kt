package cat.dam.grup2.swipe4job_app.features.candidate.state

import androidx.lifecycle.ViewModel

class AddSoftskillViewModel : ViewModel() {
    companion object { val instance = AddSoftskillViewModel() }

    var editingSoftskill: String? = null
    var editingIndex: Int = -1
}