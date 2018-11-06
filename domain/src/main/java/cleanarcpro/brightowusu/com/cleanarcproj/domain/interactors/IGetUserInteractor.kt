package cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors

import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainUser
import io.reactivex.Observable

interface IGetUserInteractor : UseCase<DomainUser> {

    fun setUserId(userId: Int)

}

