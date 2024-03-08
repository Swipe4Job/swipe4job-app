package cat.dam.grup2.swipe4job_app.features.auth

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class AuthViewModel : ViewModel() {
    var accessToken by mutableStateOf("")

    companion object { // singleton
        private var instance: AuthViewModel? = null
        fun getInstance(): AuthViewModel {
            if (instance == null) {
                instance = AuthViewModel()
            }
            return instance!!
        }
    }
}