package cat.dam.grup2.swipe4job_app.features.recruiter.models

import android.content.Context
import cat.dam.grup2.swipe4job_app.CustomError
import cat.dam.grup2.swipe4job_app.R

object SoftSkillsList {
    val softSkills = listOf(
        "COMMUNICATION",
        "LEADERSHIP",
        "TEAMWORK",
        "PROBLEM_SOLVING",
        "TIME_MANAGEMENT",
        "ADAPTABILITY",
        "CREATIVITY",
        "CRITICAL_THINKING",
        "DECISION_MAKING",
        "ATTENTION_TO_DETAIL",
        "ORGANIZATION",
        "NEGOTIATION",
        "EMOTIONAL_INTELLIGENCE",
        "FLEXIBILITY",
        "STRESS_MANAGEMENT",
        "NETWORKING",
        "CONFLICT_RESOLUTION",
        "ANALYTICAL_SKILLS",
        "CUSTOMER_SERVICE"
    )

    fun fromResourceString(context: Context, resourceString: String): String {
        val skillsArray = context.resources.getStringArray(R.array.soft_skills_array).toList()
        val desiredIndex = skillsArray.indexOf(resourceString)
        if (desiredIndex == -1) {
            throw CustomError("Cannot convert this resources string '$resourceString'")
        }

        return softSkills[desiredIndex]
    }

    fun toResourceString(context: Context, skill: String): String {
        val skillsArray = context.resources.getStringArray(R.array.soft_skills_array).toList()
        val desiredIndex = softSkills.indexOf(skill)
        if (desiredIndex == -1) {
            throw CustomError("Cannot convert this skill '$skill'")
        }

        return skillsArray[desiredIndex]
    }
}