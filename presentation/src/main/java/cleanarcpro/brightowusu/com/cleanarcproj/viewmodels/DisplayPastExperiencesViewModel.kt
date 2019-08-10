package cleanarcpro.brightowusu.com.cleanarcproj.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.mappers.MapPastExperiencesDomainToUI
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.DomainPastExperiences
import cleanarcpro.brightowusu.com.cleanarcproj.domain.exceptions.PastExperiencesDoesNotExistException
import cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors.IGetPastExperiencesInteractor
import cleanarcpro.brightowusu.com.cleanarcproj.viewmodels.states.PastExperiencesViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Bright Owusu-Amankwaa
 */
class DisplayPastExperiencesViewModel : ViewModel() {

    private val pastExperiencesStateLiveData = MutableLiveData<PastExperiencesViewState>()

    lateinit var pastExperienceInteractor: IGetPastExperiencesInteractor

    /**
     * Get the Live data past exp state
     */
    fun getDisplayPastExperiencesViewState(): LiveData<PastExperiencesViewState> {
        return pastExperiencesStateLiveData
    }

    /**
     * Loads past user work experiences.
     */
    fun loadPastExperiences(userId: Long, scope: CoroutineScope = viewModelScope) {
        scope.launch {
            pastExperienceInteractor.setUserId(userId)
            val backgroundJob = async {pastExperienceInteractor.execute(scope)}
            processResult(backgroundJob.await())
        }
    }

    private fun processResult(domainPastExperiencesResult: Pair<DomainPastExperiences?, Exception?>) {

        domainPastExperiencesResult.apply {
            if(first != null) {
                pastExperiencesExists()
                updateUI(first!!)
            } else {
                handleError(second!!)
            }
        }
    }

    private fun handleError(ex: Exception) {
        when(ex) {
            is PastExperiencesDoesNotExistException -> pastExperiencesDoesNotExist()
            else -> pastExperiencesStateLiveData.value = PastExperiencesViewState.Error(ex) // some other error
        }
    }

    private fun updateUI(user: DomainPastExperiences) {
        val pastExp = MapPastExperiencesDomainToUI.transform(user)
        pastExperiencesStateLiveData.value = PastExperiencesViewState.Success(pastExp)
    }

    private fun pastExperiencesExists() {
        pastExperiencesStateLiveData.value = PastExperiencesViewState.PastExperiencesExists()
    }

    private fun pastExperiencesDoesNotExist() {
        pastExperiencesStateLiveData.value = PastExperiencesViewState.PastExperiencesDoesNotExist()
    }
}