package cat.dam.grup2.swipe4job_app.model

data class RemoteResult(
    val `data`: List<Data>,
    val message: String,
    val success: Boolean
)