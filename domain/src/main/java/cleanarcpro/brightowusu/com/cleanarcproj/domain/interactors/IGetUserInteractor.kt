package cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors

import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainUser

interface IGetUserInteractor : UseCase<DomainUser?, Exception?> {

    fun setUserId(userId: Long)

}

