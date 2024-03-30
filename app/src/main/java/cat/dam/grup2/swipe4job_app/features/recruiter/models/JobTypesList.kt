package cat.dam.grup2.swipe4job_app.features.recruiter.models

import android.content.Context
import cat.dam.grup2.swipe4job_app.CustomError
import cat.dam.grup2.swipe4job_app.R

object JobTypesList {
    val jobTypes = listOf(
        "REMOTELY",
        "ONSITE",
        "HYBRID"
    )

    fun fromResourceString(context: Context, resourceString: String): String {
        val jobTypesList = context.resources.getStringArray(R.array.job_type_array).toList()
        val index = jobTypesList.indexOf(resourceString)
        if (index == -1) {
            throw CustomError("Cannot convert resource strings")
        }

        return jobTypes[index]
    }

    fun toResourceString(context: Context, jobType: String): String {
        val jobTypesList = context.resources.getStringArray(R.array.job_type_array).toList()
        val index = jobTypes.indexOf(jobType)
        if (index == -1) {
            throw CustomError("Cannot convert resource strings")
        }

        return jobTypesList[index]
    }
}