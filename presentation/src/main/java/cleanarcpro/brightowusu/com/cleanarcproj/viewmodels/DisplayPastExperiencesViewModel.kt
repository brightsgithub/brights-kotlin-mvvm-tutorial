package cleanarcpro.brightowusu.com.cleanarcproj.viewmodels

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.mappers.MapPastExperiencesDomainToUI
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.UIPastExperiences
import cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors.IGetPastExperiencesInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DisplayPastExperiencesViewModel : ViewModel(){

    private val disposables = CompositeDisposable()

    private val pastExperiencesLiveData = MutableLiveData<UIPastExperiences>()
    private val error = MutableLiveData<Throwable>()

    @Inject
    lateinit var pastExperienceInteractor: IGetPastExperiencesInteractor


    fun getLoadedPastExpLiveData(): LiveData<UIPastExperiences> {
        return pastExperiencesLiveData
    }


    fun loadPastExperiences(userId: Int) {
        pastExperienceInteractor.setUserId(userId)
        disposables.add(pastExperienceInteractor.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            // onNext()
                            pastExperiences ->
                            pastExperiencesLiveData.value =
                                    MapPastExperiencesDomainToUI.transform(pastExperiences)
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