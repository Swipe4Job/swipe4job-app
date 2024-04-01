package cat.dam.grup2.swipe4job_app.features.candidate.state

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class AddSoftskillViewModel : ViewModel() {
    companion object { val instance = AddSoftskillViewModel() }

    var editingSoftSkills = mutableStateListOf<String>()

    fun addSoftSkill(skill: String) {
        if (editingSoftSkills.contains(skill)) {
            return
        }

        editingSoftSkills.add(skill)
    }
}