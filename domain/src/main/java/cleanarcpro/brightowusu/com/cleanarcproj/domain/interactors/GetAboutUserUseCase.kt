package cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors

import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainAboutUser
import io.reactivex.Observable
import io.reactivex.functions.BiFunction

class GetAboutUserUseCase(
        val getProSummaryInteractor :IGetProSummaryInteractor,
        val getUserInteractor: IGetUserInteractor) : IGetAboutUserInteractor{

    override fun setUserId(userId: Int) {
        getUserInteractor.setUserId(userId!!)
        getProSummaryInteractor.setUserId(userId!!)
    }

    override fun execute(): Observable<DomainAboutUser> {

        return Observable.zip(
                getUserInteractor.execute(),
                getProSummaryInteractor.execute(),
                BiFunction { user, summary ->
                    DomainAboutUser(user, summary)
                }
        )
    }

}