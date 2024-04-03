package cat.dam.grup2.swipe4job_app.features.recruiter.models

data class OfferDataRequestDTO(
    val id: String,
    val companyName: String,
    val contractType: String,
    val departmentOrganisation: String,
    val description: String,
    val jobType: String,
    val recruiterId: String,
    val requirements: String,
    val responsibilities: String,
    val salaryRange: String,
    val skills: List<String>,
    val location: String,
    val title: String,
    val workingDay: String,
    val workingHours: String
)

data class OfferDataResponseDTO(
    val id: String,
    val companyName: String,
    val contractType: String,
    val departmentOrganization: String,
    val description: String,
    val jobType: String,
    val recruiterId: String,
    val requirements: String,
    val responsabilities: String,
    val salaryRange: String,
    val skills: List<String>,
    val location: String,
    val title: String,
    val workingDay: String,
    val workingHours: String,
    val publicationDate: String,
)