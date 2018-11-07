package cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors

import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainProfessionalSummary

interface IGetProSummaryInteractor : UseCase<DomainProfessionalSummary> {

    fun setUserId(userId: Int)
}