package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.usercv

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.mappers.MapEntityUserDetailsToDomain
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.DomainPastExperiences
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.DomainTopicsOfKnowladge
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.EntityUser
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainProfessionalSummary
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainUser
import io.reactivex.Observable


class UserRepositoryImpl(val uerCVApi: IUserCVApi) : IUserRepository{


    override fun getPastExperiences(userId: Int): Observable<DomainPastExperiences> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getProfessionalSummary(userId: Int): Observable<DomainProfessionalSummary> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getTopicsOfKnowladge(userId: Int): Observable<DomainTopicsOfKnowladge> {
        return uerCVApi.getUserDetails(userId)
                .map { entityUser: EntityUser ->  MapEntityUserDetailsToDomain.transform(entityUser)}
    }





    override fun getUser(userId: Int): Observable<DomainUser> {
        return uerCVApi.getUserDetails(userId)
                .map { entityUser: EntityUser ->  MapEntityUserDetailsToDomain.transform(entityUser)}
    }


}