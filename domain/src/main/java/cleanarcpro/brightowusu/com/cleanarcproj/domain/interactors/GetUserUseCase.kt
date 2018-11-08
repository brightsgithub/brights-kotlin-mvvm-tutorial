package cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors

import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainUser
import io.reactivex.Observable

/**
 * Get the User and perform any business logic here if needed.
 *
 * Created by Bright Owusu-Amankwaa
 */
class GetUserUseCase (val userRepository: IUserRepository) : IGetUserInteractor {


    var userId: Int? = null
    override fun setUserId(userId: Int) {
        this.userId = userId
    }

    override fun execute(): Observable<DomainUser> {

        if(userId == null) {
            throw IllegalStateException("User id cannot be null")
        }

        return userRepository.getUser(userId!!)
    }

}