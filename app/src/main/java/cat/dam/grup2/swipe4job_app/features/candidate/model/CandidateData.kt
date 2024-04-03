package cat.dam.grup2.swipe4job_app.features.candidate.model

data class CandidateData(
    val candidateId: String,
    val description: String,
    val jobExperiences: List<JobExperience>,
    val languages: List<Language>,
    val lastname: String,
    val location: String,
    val name: String,
    val softSkills: List<String>,
    val studies: List<Study>
)