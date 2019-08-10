package cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors

import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainProfessionalSummary

interface IGetProSummaryInteractor : UseCase<DomainProfessionalSummary?, Exception?> {

    fun setUserId(userId: Long)
}