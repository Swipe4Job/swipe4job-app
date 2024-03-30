package cat.dam.grup2.swipe4job_app.features.recruiter.models

import android.content.Context
import cat.dam.grup2.swipe4job_app.CustomError
import cat.dam.grup2.swipe4job_app.R

object WorkingDayTypesList {
    val workingDayTypes = listOf(
        "FULL_TIME",
        "PART_TIME",
        "FLEXIBLE"
    )

    fun fromResourceString(context: Context, resourceString: String): String {
        val skillsArray = context.resources.getStringArray(R.array.working_day_type_array).toList()
        val desiredIndex = skillsArray.indexOf(resourceString)
        if (desiredIndex == -1) {
            throw CustomError("Cannot convert this resources string '$resourceString'")
        }

        return workingDayTypes[desiredIndex]
    }

    fun toResourceString(context: Context, skill: String): String {
        val skillsArray = context.resources.getStringArray(R.array.working_day_type_array).toList()
        val desiredIndex = workingDayTypes.indexOf(skill)
        if (desiredIndex == -1) {
            throw CustomError("Cannot convert this working day '$skill'")
        }

        return skillsArray[desiredIndex]
    }
}