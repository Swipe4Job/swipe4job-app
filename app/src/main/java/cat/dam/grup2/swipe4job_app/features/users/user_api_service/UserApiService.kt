package cat.dam.grup2.swipe4job_app.features.users.user_api_service

import Criteria
import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import cat.dam.aria.retrofit.shared.criteria.CriteriaEncoder
import cat.dam.grup2.swipe4job_app.CustomError
import cat.dam.grup2.swipe4job_app.features.auth.state.AuthViewModel
import cat.dam.grup2.swipe4job_app.features.candidate.CandidateInformation
import cat.dam.grup2.swipe4job_app.features.candidate.model.CandidateData
import cat.dam.grup2.swipe4job_app.features.candidate.screens.JobExperience
import cat.dam.grup2.swipe4job_app.features.candidate.screens.LanguageLevel
import cat.dam.grup2.swipe4job_app.features.candidate.screens.LanguageSkill
import cat.dam.grup2.swipe4job_app.features.candidate.screens.Study
import cat.dam.grup2.swipe4job_app.shared.retrofit.RetrofitService
import cat.dam.grup2.swipe4job_app.features.recruiter.models.CompanyData
import cat.dam.grup2.swipe4job_app.features.recruiter.models.CompanyPost
import cat.dam.grup2.swipe4job_app.features.recruiter.models.ContractTypeOptions
import cat.dam.grup2.swipe4job_app.features.recruiter.models.JobOfferInformation
import cat.dam.grup2.swipe4job_app.features.recruiter.models.JobTypeOptions
import cat.dam.grup2.swipe4job_app.features.recruiter.models.OfferPost
import cat.dam.grup2.swipe4job_app.features.recruiter.models.SalaryRange
import cat.dam.grup2.swipe4job_app.features.recruiter.models.WorkingDayTypeOptions
import cat.dam.grup2.swipe4job_app.shared.retrofit.model.LoginResponseData
import cat.dam.grup2.swipe4job_app.shared.retrofit.model.LogoutResponseData
import cat.dam.grup2.swipe4job_app.shared.retrofit.model.RemoteResult
import cat.dam.grup2.swipe4job_app.features.users.user_api_service.model.UserData
import cat.dam.grup2.swipe4job_app.features.users.user_api_service.model.UserLogin
import cat.dam.grup2.swipe4job_app.features.users.user_api_service.model.UserLogout
import cat.dam.grup2.swipe4job_app.features.users.user_api_service.model.UserPost
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.Date

class UserApiService(val retrofit: RetrofitService) {
    suspend fun listUsers(criteria: Criteria): List<UserData> {
        val encodedCriteria = CriteriaEncoder.encodeCriteria(criteria)
        val results = retrofit.listUsers(encodedCriteria)
        return results.data
    }

    suspend fun listCompanies(criteria: Criteria): List<CompanyData> {
        val encodedCriteria = CriteriaEncoder.encodeCriteria(criteria)
        val results = retrofit.listCompanies(encodedCriteria)
        return results.data
    }


    suspend fun listOffers(criteria: Criteria): List<JobOfferInformation> {
        val encodedCriteria = CriteriaEncoder.encodeCriteria(criteria)
        val results = retrofit.listOffers(encodedCriteria)

        val format = DateTimeFormatter.ISO_INSTANT

        return results.data.map {
            val instant = Instant.from(format.parse(it.publicationDate))
            JobOfferInformation(
                companyName = it.companyName.ifEmpty { null },
                departmentOrganization = it.departmentOrganization,
                jobTitle = it.title,
                description = it.description,
                location = it.location,
                requirements = it.requirements,
                publicationDate = Date.from(instant),
                responsabilities = it.responsabilities,
                skills = it.skills,
                workingHours = it.workingHours.ifEmpty { null },
                workingDayType = WorkingDayTypeOptions.valueOf(it.workingDay),
                contractType = ContractTypeOptions.valueOf(it.contractType),
                jobType = JobTypeOptions.valueOf(it.jobType),
                salaryRange = SalaryRange.formValue(it.salaryRange)
            )
        }
    }

    suspend fun listCandidates(criteria: Criteria): List<CandidateInformation> {
        val encodedCriteria = CriteriaEncoder.encodeCriteria(criteria)
        val results = retrofit.listCandidate(encodedCriteria)
        return results.data.map { candidateData ->
            println(candidateData)
            CandidateInformation(
                description = candidateData.description,
                location = candidateData.location,
                jobExperience = candidateData.jobExperiences.map {
                    JobExperience(
                        it.position,
                        it.company,
                        it.description.ifEmpty { null },
                        it.startDate,
                        it.endDate
                    )
                },
                lastname = candidateData.lastname,
                name = candidateData.name,
                softskills = candidateData.softSkills,
                languages = candidateData.languages.map {
                    LanguageSkill(
                        it.language,
                        LanguageLevel.valueOf(it.level),
                        it.academicTitle.ifEmpty { null }
                    )
                },
                studies = candidateData.studies.map {
                    Study(it.name, it.school, it.startDate, it.endDate)
                }
            )
        }
    }

    suspend fun addUser(userPost: UserPost): Unit {
        val results = retrofit.addUser(userPost)
        return (results.data)
    }

    suspend fun addCompany(companyPost: CompanyPost): Unit {
        val results = retrofit.addCompany(companyPost)
        return (results.data)
    }

    suspend fun addOffer(offerPost: OfferPost): Unit {
        val results = retrofit.addOffer(offerPost)
        return (results.data)
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    suspend fun getMyData(): UserData {
        val token = AuthViewModel.getInstance().accessToken // get token from AuthViewModel
        try {
            val response = retrofit.getMyData("Bearer $token")
            return response.data
        } catch (e: HttpException) {
            val errorMessage = "Error en la solicitud HTTP: ${e.message}"
            throw CustomError(errorMessage)
        } catch (e: Throwable) {
            val errorMessage = "Error inesperado: ${e.message}"
            throw CustomError(errorMessage)
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    suspend fun userLogin(email: String, password: String): LoginResponseData {
        val userLogIn = UserLogin(
            email = email,
            password = password
        )
        try {
            var response = retrofit.userLogin(userLogIn)
            return response.data
        } catch (e: HttpException) {
            val errorMessage = "Error en la solicitud HTTP: ${e.message}"
            throw CustomError(errorMessage)
        } catch (e: Throwable) {
            val errorMessage = "Error inesperado: ${e.message}"
            throw CustomError(errorMessage)
        }
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    suspend fun userLogout(): RemoteResult<LogoutResponseData> {
        val userLogOut = UserLogout(
            token = "eyJhbGciOiJIUzI1NiJ9.eyJkYXRhIjp7InVzZXJJRCI6ImQ3NTI2N2QzLTllMDQtNDhhOS1hMzRhLWUyZTg0ZDdmODNiYyIsInJvbGUiOiJBRE1JTiJ9LCJ0eXBlIjoiYXV0aC51c2VyIiwia2luZCI6InJlZnJlc2giLCJqdGkiOiI1ZjQ1YTVlYy0yYjE3LTRmMjQtYWU5OS0yMDU1OWNhM2ZjZGIiLCJpYXQiOjE3MDg3MTA2MTQsImV4cCI6MTcwODczMjIxNH0.kJBJ5nUaHhGMrK1ncbWFvsB8jErV_r6h9LLhzrorKZc"
        )
        try {
            val response = retrofit.userLogout(userLogOut)
            return (response)
        } catch (e: HttpException) {
            val errorMessage = "Error en la solicitud HTTP: ${e.message}"
            throw CustomError(errorMessage)
        } catch (e: Throwable) {
            val errorMessage = "Error inesperado: ${e.message}"
            throw CustomError(errorMessage)
        }
    }
}