package cat.dam.grup2.swipe4job_app.features.recruiter.models

import android.content.Context
import cat.dam.grup2.swipe4job_app.CustomError
import cat.dam.grup2.swipe4job_app.R
import java.util.Date


sealed class SalaryRange {
    companion object {
        val salaryRanges = listOf(
            LowerThan(15_000.0),
            Between(15_000.0, 20_000.0),
            Between(20_000.0, 25_000.0),
            Between(25_000.0, 35_000.0),
            Between(35_000.0, 45_000.0),
            Between(45_000.0, 55_000.0),
            Between(55_000.0, 65_000.0),
            GreaterThan(65_000.0)
        )
    }
    class Between(val start: Double, val end: Double) : SalaryRange() {
        override fun equals(other: Any?): Boolean {
            if (other !is Between) {
                return false
            }
            return this.start == other.start && this.end == other.end
        }
    }
    class GreaterThan(val salary: Double) : SalaryRange() {
        override fun equals(other: Any?): Boolean {
            if (other !is GreaterThan) {
                return false
            }
            return this.salary == other.salary
        }
    }
    class LowerThan(val salary: Double) : SalaryRange() {
        override fun equals(other: Any?): Boolean {
            if (other !is LowerThan) {
                return false
            }

            return this.salary == other.salary
        }
    }

    fun toStringResource(context: Context): String {
        val salaryRangeIndex = salaryRanges.indexOfFirst { it.equals(this) }

        if (salaryRangeIndex == -1) {
            throw CustomError("Cannot convert salary range to string resource salary range not found")
        }

        val salaryRangesResources = context.resources.getStringArray(R.array.salary_range_array).toList()
        return salaryRangesResources[salaryRangeIndex]
    }
}



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
    val departmentOrganisation: String,
    val publicationDate: Date
)


enum class JobTypeOptions {
    Onsite,
    Remotely,
    Hybrid;

    fun toStringResource(context: Context): String {
        val stringResourceList = context.resources.getStringArray(R.array.job_type_array).toList()
        return when (this) {
            Remotely -> stringResourceList[0]
            Onsite -> stringResourceList[1]
            Hybrid -> stringResourceList[2]
        }
    }
}

enum class ContractTypeOptions {
    Freelance,
    Internship,
    Temporary,
    Indefinite,
    Other;

    fun toStringResource(context: Context): String {
        val stringResourceList = context.resources.getStringArray(R.array.contract_type_array).toList()
        return when (this) {
            Indefinite -> stringResourceList[0]
            Temporary -> stringResourceList[1]
            Freelance -> stringResourceList[2]
            Internship -> stringResourceList[3]
            Other -> stringResourceList[4]
        }
    }
}

enum class WorkingDayTypeOptions {
    FullTime,
    PartTime,
    Flexible;
    fun toStringResource(context: Context): String {
        val stringResourceList = context.resources.getStringArray(R.array.working_day_type_array).toList()
        return when (this) {
            FullTime -> stringResourceList[0]
            PartTime -> stringResourceList[1]
            Flexible -> stringResourceList[2]
        }
    }
}