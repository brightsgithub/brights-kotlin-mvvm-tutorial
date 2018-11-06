package cleanarcpro.brightowusu.com.cleanarcproj.viewmodels

import android.arch.lifecycle.ViewModel
import cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors.GetUserUseCase
import cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors.IGetUserInteractor
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainUser
import cleanarcpro.brightowusu.com.cleanarcproj.models.UIUser
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import javax.inject.Inject

class DisplayUserViewModel : ViewModel() {


    //@Inject
    lateinit var userInteractor: IGetUserInteractor<Observable<DomainUser>>


    fun loadUser() : Observable<UIUser> {
        return userInteractor.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .flatMap{
                    domainUser ->
                    Observable.just(UIUser(domainUser.email,domainUser.userName))
                }
    }
}