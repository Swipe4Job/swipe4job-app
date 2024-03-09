package cat.dam.grup2.swipe4job_app.features.users.user_api_service.model

data class UserPost(
    val email: String,
    val lastName: String,
    val name: String,
    val password: String,
    val phoneNumber: String,
    val role: String
)