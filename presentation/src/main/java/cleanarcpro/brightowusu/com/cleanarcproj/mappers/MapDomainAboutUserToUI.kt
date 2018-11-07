package cleanarcpro.brightowusu.com.cleanarcproj.mappers

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.mappers.MapDomainUserDetailsToUI
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.mappers.MapProSummaryDomainToUI
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainAboutUser
import cleanarcpro.brightowusu.com.cleanarcproj.models.UIAboutUser

class MapDomainAboutUserToUI {


    companion object {

        fun transform(domainAboutUser: DomainAboutUser): UIAboutUser {

            val uiUser = MapDomainUserDetailsToUI.transform(domainAboutUser.domainUser)
            val uiProSummary = MapProSummaryDomainToUI.transform(domainAboutUser.domainSummary)

            return UIAboutUser(uiUser, uiProSummary)

        }

    }

}