package cleanarcpro.brightowusu.com.cleanarcproj.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors.IGetUserInteractor
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainUser
import cleanarcpro.brightowusu.com.cleanarcproj.models.UIUser
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DisplayUserViewModel : ViewModel() {

    private val disposables = CompositeDisposable()

    private val userLiveData = MutableLiveData<UIUser>()
    private val error = MutableLiveData<Throwable>()

    @Inject
    lateinit var userInteractor: IGetUserInteractor


    fun getLoadedUserLiveData(): LiveData<UIUser> {
        return userLiveData
    }


    fun loadUser(userId: Int) {
        userInteractor.setUserId(userId)
        disposables.add(userInteractor.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            // onNext()
                            domainUser ->
                            userLiveData.value = UIUser(domainUser.email,domainUser.name)
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