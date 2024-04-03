package cat.dam.grup2.swipe4job_app.features.recruiter.models

import android.content.Context
import cat.dam.grup2.swipe4job_app.CustomError
import cat.dam.grup2.swipe4job_app.R

object LanguageList {
    val languages = listOf<String>(
        "ARABIC",
        "BENGALI",
        "CHINESE",
        "CZECH",
        "DANISH",
        "DUTCH",
        "ENGLISH",
        "ESTONIAN",
        "FINNISH",
        "FRENCH",
        "GERMAN",
        "GREEK",
        "HEBREW",
        "HINDI",
        "HUNGARIAN",
        "INDONESIAN",
        "ITALIAN",
        "JAPANESE",
        "KOREAN",
        "LATVIAN",
        "LITHUANIAN",
        "MALAY",
        "NORWEGIAN",
        "POLISH",
        "PORTUGUESE",
        "ROMANIAN",
        "RUSSIAN",
        "SLOVAK",
        "SLOVENIAN",
        "SPANISH",
        "SWEDISH",
        "THAI",
        "TURKISH",
        "UKRAINIAN",
        "VIETNAMESE",
        "OTHER",
    )
    fun toResourceString(context: Context, language: String): String {
        val languagesArray = context.resources.getStringArray(R.array.languages_array).toList()
        val desiredIndex = languages.indexOf(language)
        if (desiredIndex == -1) {
            throw CustomError("Cannot convert this language '$language'")
        }

        return languagesArray[desiredIndex]
    }
}