package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.mappers

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.DomainPastExperience
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.DomainPastExperiences
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.EntityPastExperiences

class MapPastExperiencesEntityToDomain {

    companion object {
        fun transform(entityPastExperiences: EntityPastExperiences) : DomainPastExperiences{
            val list = ArrayList<DomainPastExperience>()
            entityPastExperiences.pastExperiences.forEach {

                val pastExp = DomainPastExperience(
                        it.id,
                        it.companyName,
                        it.roleName,
                        it.datesStart,
                        it.dateEnd,
                        it.responsibilities,
                        it.companyLogo)

                list.add(pastExp)
            }
            return DomainPastExperiences(list)
        }
    }

}