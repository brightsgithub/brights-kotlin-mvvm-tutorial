package cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.DomainPastExperiences
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import cleanarcpro.brightowusu.com.cleanarcproj.domain.exceptions.PastExperiencesDoesNotExistException
import com.sun.istack.internal.NotNull
import kotlinx.coroutines.CoroutineScope
import kotlin.Exception

/**
 * Get the users past experiences and perform any business logic here if needed.
 *
 * Created by Bright Owusu-Amankwaa
 */
class GetPastExperiencesUseCase(val userRepository: IUserRepository) : IGetPastExperiencesInteractor {

    override suspend fun execute(scope: CoroutineScope): Pair<DomainPastExperiences?, Exception?> {

        return try {

            if(userId == null) {
                throw IllegalStateException("User id cannot be null")
            }

            val pastExp = userRepository.getPastExperiences(userId!!)

            if(pastExp.pastExperiences.isEmpty()) {
                throw PastExperiencesDoesNotExistException()
            }

            Pair(pastExp, null)
        } catch (exception: Exception) {
            Pair(null, exception)
        }
    }


    var userId: Long? = null
    override fun setUserId(userId: Long) {
        this.userId = userId
    }

}