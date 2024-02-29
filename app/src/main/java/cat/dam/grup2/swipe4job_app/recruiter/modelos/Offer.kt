package cat.dam.grup2.swipe4job_app.recruiter.modelos


sealed class SalaryRange {
    class Between(val start: Double, val end: Double) : SalaryRange()
    class GreaterThan(val salary: Double) : SalaryRange()
    class LowerThan(val salary: Double) : SalaryRange()
}

val salaryRanges = listOf<SalaryRange>(
    SalaryRange.LowerThan(15_000.0),
    SalaryRange.Between(15_000.0, 20_000.0),
    SalaryRange.Between(20_000.0, 25_000.0),
    SalaryRange.Between(25_000.0, 35_000.0),
    SalaryRange.Between(35_000.0, 45_000.0),
    SalaryRange.Between(45_000.0, 55_000.0),
    SalaryRange.Between(55_000.0, 65_000.0),
    SalaryRange.GreaterThan(65_000.0)
)

data class JobOfferInformation(
    val jobTitle: String,
    val location: String,
    val companyName: String? = null,
    val description: String,
    val responsabilities: String,
    val requirements: String,
    val jobType: JobTypeOptions,
    val contractType: ContractTypeOptions,
    val workingDayType: WorkingDayTypeOptions,
    val skills: List<String>,
    val salaryRange: SalaryRange,
    val workingHours: String? = null,
    val departmentOrganisation: String
)


enum class JobTypeOptions {
    Onsite,
    Remotely,
    Hybrid
}

enum class ContractTypeOptions {
    Freelance,
    Internship,
    Temporary,
    Indefinite,
    Other
}

enum class WorkingDayTypeOptions {
    FullTime,
    PartTime,
    Flexible
}