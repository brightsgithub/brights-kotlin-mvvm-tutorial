package cleanarcpro.brightowusu.com.cleanarcproj.data.repository

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.EntityUser
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainUser
import io.reactivex.Observable


class UserRepositoryImpl : IUserRepository{
    override fun getUser(): Observable<DomainUser> {
        return getUserFromApi()
    }


    private fun getUserfromDB() : Observable<DomainUser> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun getUserFromApi() : Observable<DomainUser> {
        val entityUser = EntityUser("The username", "some@someEmail2.com", "", "")
        return Observable.just(DomainUser(entityUser.userName, entityUser.email))
    }
}