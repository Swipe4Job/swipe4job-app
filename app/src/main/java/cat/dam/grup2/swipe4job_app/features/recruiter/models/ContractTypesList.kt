package cat.dam.grup2.swipe4job_app.features.recruiter.models

import android.content.Context
import cat.dam.grup2.swipe4job_app.CustomError
import cat.dam.grup2.swipe4job_app.R

object ContractTypesList {
    val contractTypes = listOf(
        "INDEFINITE",
        "TEMPORARY",
        "FREELANCE",
        "INTERNSHIP",
        "OTHER"
    )

    fun fromStringResource(context: Context, resourceString: String): String {
        val contractTypesStrings = context.resources.getStringArray(R.array.contract_type_array).toList()
        val index = contractTypesStrings.indexOf(resourceString)
        if (index == -1) {
            throw CustomError("Cannot convert '$resourceString'")
        }

        return contractTypes[index]
    }

    fun toResourceString(context: Context, contractType: String): String {
        val contractTypesStrings = context.resources.getStringArray(R.array.contract_type_array).toList()
        val index = contractTypes.indexOf(contractType)
        if (index == -1) {
            throw CustomError("Cannot convert '$contractType'")
        }

        return contractTypesStrings[index]
    }
}