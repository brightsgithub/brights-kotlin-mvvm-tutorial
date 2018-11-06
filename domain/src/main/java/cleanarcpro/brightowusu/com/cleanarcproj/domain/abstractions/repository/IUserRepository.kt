package cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository

import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainUser
import io.reactivex.Observable

interface IUserRepository {

    fun getUser(userId: Int): Observable<DomainUser>
}