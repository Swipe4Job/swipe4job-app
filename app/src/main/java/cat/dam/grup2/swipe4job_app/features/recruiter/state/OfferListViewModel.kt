package cat.dam.grup2.swipe4job_app.features.recruiter.state

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cat.dam.grup2.swipe4job_app.CustomError
import cat.dam.grup2.swipe4job_app.features.recruiter.models.ContractTypeOptions
import cat.dam.grup2.swipe4job_app.features.recruiter.models.JobOfferInformation
import cat.dam.grup2.swipe4job_app.features.recruiter.models.JobTypeOptions
import cat.dam.grup2.swipe4job_app.features.recruiter.models.SalaryRange
import cat.dam.grup2.swipe4job_app.features.recruiter.models.WorkingDayTypeOptions
import cat.dam.grup2.swipe4job_app.userApiService
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class OfferListViewModel : ViewModel() {
    companion object {
        val instance = OfferListViewModel()
    }
    var offerList = mutableStateListOf<JobOfferInformation>()

    init {
        viewModelScope.launch {
            val offers = userApiService.listOffers(Criteria.NONE())

            // Converteix la llista d'ofertes de l'API a SnapshotStateList de JobOfferInformation
            val convertedOffers = offers.map { offerData ->
                println(offerData.title)
                JobOfferInformation(
                    jobTitle = offerData.title,
                    location = offerData.location,
                    companyName = offerData.companyName,
                    description = offerData.description,
                    responsabilities = offerData.responsabilities,
                    requirements = offerData.requirements,
                    jobType = JobTypeOptions.valueOf(offerData.jobType),
                    contractType = ContractTypeOptions.valueOf(offerData.contractType),
                    workingDayType = WorkingDayTypeOptions.valueOf(offerData.workingDay),
                    skills = offerData.skills,
                    salaryRange = when (offerData.salaryRange) {
                        "LOWER_THAN_15" -> SalaryRange.LowerThan(15_000.0)
                        "BETWEEN_15_20" -> SalaryRange.Between(15_000.0, 20_000.0)
                        "BETWEEN_20_25" -> SalaryRange.Between(20_000.0, 25_000.0)
                        "BETWEEN_25_35" -> SalaryRange.Between(25_000.0, 35_000.0)
                        "BETWEEN_35_45" -> SalaryRange.Between(35_000.0, 45_000.0)
                        "BETWEEN_45_55" -> SalaryRange.Between(45_000.0, 55_000.0)
                        "BETWEEN_55_65" -> SalaryRange.Between(55_000.0, 65_000.0)
                        "GREATER_THAN_65" -> SalaryRange.GreaterThan(65_000.0)
                        else -> throw CustomError("Unknown salary range: ${offerData.salaryRange}")
                    },
                    workingHours = offerData.workingHours,
                    departmentOrganization = offerData.departmentOrganization,
                    publicationDate = Date.from(Instant.parse(offerData.publicationDate))
                )
            }
            offerList.addAll(convertedOffers)
        }
    }
}