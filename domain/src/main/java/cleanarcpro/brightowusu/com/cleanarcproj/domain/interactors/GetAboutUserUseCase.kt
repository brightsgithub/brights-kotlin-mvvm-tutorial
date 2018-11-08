package cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors

import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainAboutUser
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

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

    private var userId: Int? = null

    override fun setUserId(userId: Int) {
        this.userId = userId
        getUserInteractor.setUserId(userId)
        getProSummaryInteractor.setUserId(userId)
    }

    override fun execute(): Observable<DomainAboutUser> {

        if(userId == null) {
            throw IllegalStateException("User id cannot be null")
        }

        return Observable.zip(
                getUserInteractor.execute(),
                getProSummaryInteractor.execute(),
                BiFunction { user, summary ->
                    DomainAboutUser(user, summary)
                }
        )
    }

}