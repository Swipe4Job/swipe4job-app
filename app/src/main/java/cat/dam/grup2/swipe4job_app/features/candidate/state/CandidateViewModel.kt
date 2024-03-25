package cat.dam.grup2.swipe4job_app.features.candidate.state

import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class CandidateViewModel : ViewModel() {
    val imageUri = mutableStateOf<Uri?>(null)
}