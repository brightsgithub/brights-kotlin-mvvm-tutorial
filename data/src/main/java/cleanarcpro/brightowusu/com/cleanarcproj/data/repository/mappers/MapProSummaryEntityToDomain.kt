package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.mappers

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.EntityProfessionalSummary
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainProfessionalSummary

class MapProSummaryEntityToDomain {

    companion object {

        fun transform(entityProfessionalSummary: EntityProfessionalSummary):
                DomainProfessionalSummary {

            return DomainProfessionalSummary(
                    entityProfessionalSummary.userId,
                    entityProfessionalSummary.professionalSummary)

        }

    }
}