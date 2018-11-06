package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.usercv

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.mappers.MapEntityUserDetailsToDomain
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.EntityUser
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainUser
import io.reactivex.Observable


class UserRepositoryImpl(val uerCVApi: IUserCVApi) : IUserRepository{
    override fun getUser(): Observable<DomainUser> {
        return getUserFromApi()
    }


    private fun getUserfromDB() : Observable<DomainUser> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun getUserFromApi() : Observable<DomainUser> {
        return uerCVApi.getUserDetails("1")
                .map { entityUser: EntityUser ->  MapEntityUserDetailsToDomain.transform(entityUser)}

    }
}