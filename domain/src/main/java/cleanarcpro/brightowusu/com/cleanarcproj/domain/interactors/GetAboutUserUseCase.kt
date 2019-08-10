package cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors

import cleanarcpro.brightowusu.com.cleanarcproj.domain.exceptions.ProSumDoesNotExistException
import cleanarcpro.brightowusu.com.cleanarcproj.domain.exceptions.UserDoesNotExistException
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainAboutUser
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainProfessionalSummary
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainUser
import com.sun.istack.internal.NotNull
import kotlinx.coroutines.*

/**
 * This is a combination of various UseCases.
 * This allows each UseCase to be obtained the correct way i.e.
 * each UseCase will perform its own needed isolated business logic, within itself.
 * This class just combines the result, which we refer to as About user information.
 *
 * Created by Bright Owusu-Amankwaa
 */
class GetAboutUserUseCase(
        val getProSummaryInteractor :IGetProSummaryInteractor,
        val getUserInteractor: IGetUserInteractor) : IGetAboutUserInteractor{

    private var userId: Long? = null

    override fun setUserId(userId: Long) {
        this.userId = userId
        getUserInteractor.setUserId(userId)
        getProSummaryInteractor.setUserId(userId)
    }

    override suspend fun execute(@NotNull scope: CoroutineScope): Pair<DomainAboutUser?, Exception?> {

        return try {

            if(userId == null) {
                throw IllegalStateException("User id cannot be null")
            }


            getUserInteractor.setUserId(userId!!)
            getProSummaryInteractor.setUserId(userId!!)

            // These ARE dependant, so execute in sequentially. This is so the backend gets a chance
            // to store the USER first before the summary which uses user id, does not break db constraints
            val domainUser = scope.async { getUserInteractor.execute(scope)}.await()
            val proSummary = scope.async { getProSummaryInteractor.execute(scope)}.await()

            val aboutUser = createDomainAboutUser(domainUser, proSummary)
            Pair(aboutUser, null)

        } catch (exe: Exception) {
            Pair(null, exe)
        }
    }

    // Validate, create or throw exception
    private fun createDomainAboutUser(
            domainUserResult: Pair<DomainUser?, Exception?>,
            domainProfessionalSummaryResult: Pair<DomainProfessionalSummary?, Exception?>
    ): DomainAboutUser {

        // if there is no exception but our model is null, throw an exception
        if(domainUserResult.first == null) {
            throw UserDoesNotExistException()
        }

        // If there is an exception, pass it up
        if(domainUserResult.second != null) {
            throw domainUserResult.second!!
        }

        if(domainProfessionalSummaryResult.first == null) {
            throw ProSumDoesNotExistException()
        }

        if(domainProfessionalSummaryResult.second != null) {
            throw domainProfessionalSummaryResult.second!!
        }

        return DomainAboutUser(domainUserResult.first!!, domainProfessionalSummaryResult.first!!)
    }


}

