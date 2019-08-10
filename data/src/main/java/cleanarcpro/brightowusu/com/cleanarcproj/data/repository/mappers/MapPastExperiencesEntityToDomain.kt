package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.mappers

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.DomainPastExperience
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.DomainPastExperiences
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.EntityPastExperience
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.EntityPastExperiences

class MapPastExperiencesEntityToDomain {

    companion object {

        fun transform(entityPastExperiences: EntityPastExperiences) : DomainPastExperiences{
            return transform(entityPastExperiences.pastExperiences)
        }

        fun transform(pastExperiences: List<EntityPastExperience>) : DomainPastExperiences{
            val list = ArrayList<DomainPastExperience>()
            pastExperiences.forEach {

                val pastExp = DomainPastExperience(
                        it.id,
                        it.companyName,
                        it.roleName,
                        it.datesStart,
                        it.dateEnd,
                        it.responsibilities,
                        it.companyLogo,
                        it.techUsed)

                list.add(pastExp)
            }
            return DomainPastExperiences(list)
        }
    }

}