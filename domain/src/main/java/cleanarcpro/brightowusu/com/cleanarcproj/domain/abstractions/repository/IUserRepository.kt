package cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.DomainPastExperiences
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.DomainTopicsOfKnowledge
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainProfessionalSummary
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainUser
import io.reactivex.Observable

/**
 * To be implemented by another layer
 * Created by Bright Owusu-Amankwaa
 */
interface IUserRepository {

    fun getUser(userId: Int): Observable<DomainUser>

    fun getPastExperiences(userId: Int): Observable<DomainPastExperiences>

    fun getProfessionalSummary(userId: Int): Observable<DomainProfessionalSummary>

    fun getTopicsOfKnowladge(userId: Int): Observable<DomainTopicsOfKnowledge>

}