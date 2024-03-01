package cat.dam.grup2.swipe4job_app.shared.retrofit.model

data class RemoteResult<T>(
    val data: T,
    val message: String,
    val success: Boolean,
    val errorCode: Int?
)