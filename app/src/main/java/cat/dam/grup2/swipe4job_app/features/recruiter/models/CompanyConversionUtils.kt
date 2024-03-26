package cat.dam.grup2.swipe4job_app.features.recruiter.models

import android.content.Context
import cat.dam.grup2.swipe4job_app.CustomError
import cat.dam.grup2.swipe4job_app.R

fun Context.companySizeFromStringResource(resourceString: String): String {
    val companySizeList = this.resources.getStringArray(R.array.company_size_array).toList()

    return when (resourceString) {
        companySizeList[0] -> "LESS_10"
        companySizeList[1] -> "BETWEEN_10_49"
        companySizeList[2] -> "BETWEEN_50_249"
        companySizeList[3] -> "MORE_250"
        else -> throw CustomError("Unexpected company size string resource '$resourceString'")
    }
}

fun Context.companySectorFromStringResource(resourceString: String): String {
    val companySectorList = this.resources.getStringArray(R.array.sectors_array).toList()

    return when (resourceString) {
        companySectorList[0] -> "ADMINAUX"
        companySectorList[1] -> "AGRICULTURE"
        companySectorList[2] -> "ARTISTIC"
        companySectorList[3] -> "COMMERCE"
        companySectorList[4] -> "CONSTRUCTION"
        companySectorList[5] -> "CONSUMERGOODS"
        companySectorList[6] -> "ECOMMERCE"
        companySectorList[7] -> "EDUCATION"
        companySectorList[8] -> "ENERGYENVIROMENT"
        companySectorList[9] -> "ENERGYSUPPLY"
        companySectorList[10] -> "FINANCEINSURANCE"
        companySectorList[11] -> "HOTEL"
        companySectorList[12] -> "INFO_TECHNO"
        companySectorList[13] -> "INTERNET"
        companySectorList[14] -> "MANUFACTURING"
        companySectorList[15] -> "PRO_SCIENTIFIC"
        companySectorList[16] -> "SPORTS"
        companySectorList[17] -> "TRANSPORT_STORAGE"
        companySectorList[18] -> "REALESTATE"
        else -> throw CustomError("Unexpected company size string resource '$resourceString'")
    }
}
