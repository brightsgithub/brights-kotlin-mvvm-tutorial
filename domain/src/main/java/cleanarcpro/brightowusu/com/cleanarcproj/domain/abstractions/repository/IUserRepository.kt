package cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository

import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainUser
import rx.Observable

interface IUserRepository {

    fun getUser(): Observable<DomainUser>
}