package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.mappers

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.EntityUser
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainUser

class MapEntityUserDetailsToDomain {

    companion object {
        fun transform(entityUser: EntityUser) : DomainUser {

            return DomainUser(
                    entityUser.userId,
                    entityUser.userName,
                    entityUser.email,
                    entityUser.phone)
        }
    }
}