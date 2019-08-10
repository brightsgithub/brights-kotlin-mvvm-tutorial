package cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors

import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainProfessionalSummary
import kotlinx.coroutines.CoroutineScope

/**
 * Get the users summary and perform any business logic here if needed.
 *
 * Created by Bright Owusu-Amankwaa
 */
class GetProSummaryUseCase(val userRepository: IUserRepository) : IGetProSummaryInteractor {

    override suspend fun execute(scope: CoroutineScope): Pair<DomainProfessionalSummary?, Exception?> {
        return try {

            if(userId == null) {
                throw IllegalStateException("User id cannot be null")
            }

            Pair(userRepository.getProfessionalSummary(userId!!), null)
        } catch (exc: Exception) {
            Pair(null, exc)
        }
    }

    var userId: Long? = null
    override fun setUserId(userId: Long) {
        this.userId = userId
    }
}