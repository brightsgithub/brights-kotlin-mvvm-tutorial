package cleanarcpro.brightowusu.com.cleanarcproj.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cleanarcpro.brightowusu.com.cleanarcproj.domain.exceptions.ProSumDoesNotExistException
import cleanarcpro.brightowusu.com.cleanarcproj.domain.exceptions.UserDoesNotExistException
import cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors.IGetAboutUserInteractor
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainAboutUser
import cleanarcpro.brightowusu.com.cleanarcproj.mappers.MapDomainAboutUserToUI
import cleanarcpro.brightowusu.com.cleanarcproj.viewmodels.states.DisplayUserViewState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

/**
 * Created by Bright Owusu-Amankwaa
 */
class DisplayUserViewModel : ViewModel() {

    private val displayUserStateLiveData = MutableLiveData<DisplayUserViewState>()

    lateinit var aboutUserInteractor: IGetAboutUserInteractor

    /**
     * Gets the LiveData display user state
     */
    fun getDisplayUserState(): LiveData<DisplayUserViewState> {
        return displayUserStateLiveData
    }

    /**
     * Loads user and summary information
     */
    fun loadAboutUserInfo(userId: Long, scope: CoroutineScope = viewModelScope) {
        scope.launch {
            aboutUserInteractor.setUserId(userId)
            val backgroundJob = async { aboutUserInteractor.execute(scope)}
            processResult(backgroundJob.await())
        }
    }

    private fun processResult(domainAboutUserResult: Pair<DomainAboutUser?, Exception?>) {

        domainAboutUserResult.apply {
            if(first != null) {
                userExists()
                updateUI(first!!)
            } else {
                handleError(second!!)
            }
        }
    }

    private fun handleError(ex: Exception) {
        when(ex) {
            is ProSumDoesNotExistException,
            is UserDoesNotExistException -> userDoesNotExist()
            else -> displayUserStateLiveData.value = DisplayUserViewState.Error(ex) // some other error
        }
    }

    private fun updateUI(domainAboutUser: DomainAboutUser) {
        val uiUser = MapDomainAboutUserToUI.transform(domainAboutUser)
        displayUserStateLiveData.value = DisplayUserViewState.Success(uiUser)
    }

    private fun userExists() {
        displayUserStateLiveData.value = DisplayUserViewState.UserExists()
    }

    private fun userDoesNotExist() {
        displayUserStateLiveData.value = DisplayUserViewState.UserDoesNotExist()
    }
}