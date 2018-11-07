package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.mappers

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.EntityUser
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainUser
import cleanarcpro.brightowusu.com.cleanarcproj.models.UIUser

class MapDomainUserDetailsToUI {

    companion object {
        fun transform(domainUser: DomainUser) : UIUser {

            return UIUser(
                    domainUser.userId,
                    domainUser.name,
                    domainUser.email,
                    domainUser.phone)
        }
    }
}