package cat.dam.grup2.swipe4job_app.features.recruiter.models

import android.content.Context
import cat.dam.grup2.swipe4job_app.CustomError
import cat.dam.grup2.swipe4job_app.R

object SalaryRangeList {
    val salaryRange = listOf(
        "LOWER_THAN_15",
        "BETWEEN_15_20",
        "BETWEEN_20_25",
        "BETWEEN_25_35",
        "BETWEEN_35_45",
        "BETWEEN_45_55",
        "BETWEEN_55_65",
        "GREATER_THAN_65"
    )

    fun fromResourceString(context: Context, resourceString: String): String {
        val skillsArray = context.resources.getStringArray(R.array.salary_range_array).toList()
        val desiredIndex = skillsArray.indexOf(resourceString)
        if (desiredIndex == -1) {
            throw CustomError("Cannot convert this resources string '$resourceString'")
        }

        return salaryRange[desiredIndex]
    }

    fun toResourceString(context: Context, salary: String): String {
        val salaryArray = context.resources.getStringArray(R.array.salary_range_array).toList()
        val desiredIndex = salaryRange.indexOf(salary)
        if (desiredIndex == -1) {
            throw CustomError("Cannot convert this salary range '$salary'")
        }

        return salaryArray[desiredIndex]
    }
}