package cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.DomainPastExperiences
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainProfessionalSummary
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainUser

/**
 * To be implemented by another layer
 * Created by Bright Owusu-Amankwaa
 */
interface IUserRepository {

    suspend fun getUser(userId: Long): DomainUser

    suspend fun getPastExperiences(userId: Long): DomainPastExperiences

    suspend fun getProfessionalSummary(userId: Long): DomainProfessionalSummary

}