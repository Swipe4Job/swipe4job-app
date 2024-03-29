package cat.dam.grup2.swipe4job_app.features.recruiter.models

data class OfferPost(
    val companyName: String,
    val contractType: String,
    val departmentOrganisation: String,
    val description: String,
    val jobType: String,
    val publicationDate: String,
    val recruiterId: String,
    val requirements: String,
    val responsibilities: String,
    val salaryRange: String,
    val skills: List<String>,
    val title: String,
    val workingDay: String,
    val workingHours: String
)