package cat.dam.grup2.swipe4job_app.features.candidate.state

import androidx.lifecycle.ViewModel
import cat.dam.grup2.swipe4job_app.features.candidate.screens.LanguageSkill

class AddLanguageViewModel : ViewModel() {
    companion object { val instance = AddLanguageViewModel() }

    var editingLanguage: LanguageSkill? = null
    var editingIndex: Int = -1
}