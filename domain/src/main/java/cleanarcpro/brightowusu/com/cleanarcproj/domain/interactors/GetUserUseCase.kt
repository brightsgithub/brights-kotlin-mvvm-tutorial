package cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors

import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainUser
import kotlinx.coroutines.CoroutineScope

/**
 * Get the User and perform any business logic here if needed.
 *
 * Created by Bright Owusu-Amankwaa
 */
class GetUserUseCase (val userRepository: IUserRepository) : IGetUserInteractor {

    var userId: Long? = null
    override fun setUserId(userId: Long) {
        this.userId = userId
    }

    override suspend fun execute(scope: CoroutineScope) : Pair<DomainUser?, Exception?>{

        return try {

            if(userId == null) {
                throw IllegalStateException("User id cannot be null")
            }

            val user = userRepository.getUser(userId!!)
            Pair(user, null)
        } catch (exp: Exception) {
            Pair(null, exp)
        }
    }
}