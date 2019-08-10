package cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors

import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainAboutUser

interface IGetAboutUserInteractor : UseCase<DomainAboutUser, Exception>{

    fun setUserId(userId: Long)
}