package cleanarcpro.brightowusu.com.cleanarcproj.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.mappers.MapDomainUserDetailsToUI
import cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors.IGetAboutUserInteractor
import cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors.IGetUserInteractor
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainUser
import cleanarcpro.brightowusu.com.cleanarcproj.mappers.MapDomainAboutUserToUI
import cleanarcpro.brightowusu.com.cleanarcproj.models.UIAboutUser
import cleanarcpro.brightowusu.com.cleanarcproj.models.UIUser
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DisplayUserViewModel : ViewModel() {

    private val disposables = CompositeDisposable()

    private val aboutUserLiveData = MutableLiveData<UIAboutUser>()
    private val error = MutableLiveData<Throwable>()

    @Inject
    lateinit var aboutUserInteractor: IGetAboutUserInteractor


    fun getLoadedUserLiveData(): LiveData<UIAboutUser> {
        return aboutUserLiveData
    }


    fun loadAboutUserInfo(userId: Int) {
        aboutUserInteractor.setUserId(userId)
        disposables.add(aboutUserInteractor.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            // onNext()
                            domainAboutUser ->
                            aboutUserLiveData.value = MapDomainAboutUserToUI.transform(domainAboutUser)
                        },
                        {
                            // onError()
                            t ->
                            t.printStackTrace()
                            error.value = t
                        },
                        {
                            // onComplete()
                        }))
    }

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

}