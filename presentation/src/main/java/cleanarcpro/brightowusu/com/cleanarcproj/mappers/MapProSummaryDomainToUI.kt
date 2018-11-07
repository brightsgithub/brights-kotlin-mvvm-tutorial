package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.mappers

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.EntityProfessionalSummary
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainProfessionalSummary
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.UIProfessionalSummary

class MapProSummaryDomainToUI {

    companion object {

        fun transform(domainProfessionalSummary: DomainProfessionalSummary):
                UIProfessionalSummary {

            return UIProfessionalSummary(
                    domainProfessionalSummary.userId,
                    domainProfessionalSummary.professionalSummary)

        }

    }
}