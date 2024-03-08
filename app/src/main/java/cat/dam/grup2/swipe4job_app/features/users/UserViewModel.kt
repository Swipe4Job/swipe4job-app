package cat.dam.grup2.swipe4job_app.features.users

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import cat.dam.grup2.swipe4job_app.features.users.user_api_service.model.UserData

class UserViewModel : ViewModel() {
    var userData by mutableStateOf<UserData?>(null)
    companion object {
        private var instance: UserViewModel? = null
        fun getInstance(): UserViewModel {
            if (instance == null) {
                instance = UserViewModel()
            }
            return instance!!
        }

    }
}