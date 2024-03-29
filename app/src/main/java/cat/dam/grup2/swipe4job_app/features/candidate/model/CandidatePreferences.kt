package cat.dam.grup2.swipe4job_app.features.candidate.model

import cat.dam.grup2.swipe4job_app.features.recruiter.models.ContractTypeOptions
import cat.dam.grup2.swipe4job_app.features.recruiter.models.JobTypeOptions
import cat.dam.grup2.swipe4job_app.features.recruiter.models.SalaryRange
import cat.dam.grup2.swipe4job_app.features.recruiter.models.WorkingDayTypeOptions

data class CandidatePreferences (
    val salaryRange: SalaryRange,
    val workingDayType: WorkingDayTypeOptions,
    val jobTypeOptions: JobTypeOptions,
    val contractTypeOptions: ContractTypeOptions
)