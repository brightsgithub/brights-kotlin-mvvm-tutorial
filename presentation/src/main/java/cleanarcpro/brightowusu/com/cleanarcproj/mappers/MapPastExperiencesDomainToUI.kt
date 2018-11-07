package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.mappers

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.DomainPastExperiences
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.UIPastExperience
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.UIPastExperiences
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.EntityPastExperiences

class MapPastExperiencesDomainToUI {

    companion object {
        fun transform(domainPastExperiences: DomainPastExperiences) : UIPastExperiences{
            val list = ArrayList<UIPastExperience>()
            domainPastExperiences.pastExperiences.forEach {

                val pastExp = UIPastExperience(
                        it.id,
                        it.companyName,
                        it.roleName,
                        it.datesStart,
                        it.dateEnd,
                        it.responsibilities,
                        it.companyLogo)

                list.add(pastExp)
            }
            return UIPastExperiences(list)
        }
    }

}