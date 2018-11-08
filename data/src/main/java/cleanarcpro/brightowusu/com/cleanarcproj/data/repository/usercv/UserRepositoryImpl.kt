package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.usercv

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.mappers.MapEntityTopicsToDomain
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.mappers.MapEntityUserDetailsToDomain
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.mappers.MapPastExperiencesEntityToDomain
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.mappers.MapProSummaryEntityToDomain
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.*
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainProfessionalSummary
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainUser
import io.reactivex.Observable

/**
 * UserRepositoryImpl -  decides where our data comes from, i.e. database, network, shared pref etc.
 * TODO - Use Room DB for single source of truth.
 */
class UserRepositoryImpl(val uerCVApi: IUserCVApi) : IUserRepository{

    override fun getUser(userId: Int): Observable<DomainUser> {
        return uerCVApi.getUserDetails(userId)
                .map { entityUser: EntityUser ->  MapEntityUserDetailsToDomain.transform(entityUser)}
    }

    override fun getPastExperiences(userId: Int): Observable<DomainPastExperiences> {
        return uerCVApi.getPastExperiences(userId)
                .map { entity: EntityPastExperiences ->  MapPastExperiencesEntityToDomain.transform(entity)}
    }

    override fun getProfessionalSummary(userId: Int): Observable<DomainProfessionalSummary> {
        return uerCVApi.getProfessionalSummary(userId)
                .map { entity: EntityProfessionalSummary ->  MapProSummaryEntityToDomain.transform(entity)}
    }

    override fun getTopicsOfKnowladge(userId: Int): Observable<DomainTopicsOfKnowledge> {
        return uerCVApi.getTopicsOfKnowladge(userId)
                .map { entity: EntityTopicsOfKnowledge ->  MapEntityTopicsToDomain.transform(entity)}
    }
}