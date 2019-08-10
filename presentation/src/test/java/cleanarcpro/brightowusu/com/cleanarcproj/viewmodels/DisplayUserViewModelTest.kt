package cleanarcpro.brightowusu.com.cleanarcproj.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors.IGetAboutUserInteractor
import org.junit.Before
import org.junit.runner.RunWith

import org.junit.Rule
import org.mockito.Mock
import androidx.lifecycle.Observer
import cleanarcpro.brightowusu.com.cleanarcproj.domain.exceptions.UserDoesNotExistException
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainAboutUser
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainProfessionalSummary
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainUser
import cleanarcpro.brightowusu.com.cleanarcproj.mappers.MapDomainAboutUserToUI
import cleanarcpro.brightowusu.com.cleanarcproj.viewmodels.states.DisplayUserViewState
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.junit.MockitoJUnitRunner


/**
 * Created by Bright Owusu-Amankwaa on 2019-08-28.
 */
@RunWith(MockitoJUnitRunner::class)
class DisplayUserViewModelTest {

    @Rule @JvmField
    var instantExecutorRule = InstantTaskExecutorRule() //Very very important

    @Mock
    lateinit var aboutUserInteractor: IGetAboutUserInteractor

    @Mock
    lateinit var aboutUserObserver: Observer<DisplayUserViewState>


    lateinit var displayUserViewModel: DisplayUserViewModel


    @Before
    fun setUp() {
        displayUserViewModel = DisplayUserViewModel()
        displayUserViewModel.aboutUserInteractor = aboutUserInteractor
    }


    @ExperimentalCoroutinesApi
    @Test
    fun testGetAboutUserHappyCase() = runBlockingTest {
        val userId = 1L
        val scope = this

        // Given
        val domainUser = getSomAboutUser(userId)
        given{
            runBlocking { aboutUserInteractor.execute(scope) }
        }.willReturn(Pair(domainUser, null))

        val uiUser = MapDomainAboutUserToUI.transform(domainUser)

        // When we call loadAboutUserInfo
        displayUserViewModel.getDisplayUserState().observeForever(aboutUserObserver)
        displayUserViewModel.loadAboutUserInfo(userId, scope)


        // Then - verify that the onChanged was called with the correct values
        verify(aboutUserObserver).onChanged(DisplayUserViewState.UserExists())
        verify(aboutUserObserver).onChanged(DisplayUserViewState.Success(uiUser))
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testUserDoesNotExist() = runBlockingTest {
        val userId = 1L
        val scope = this

        // Given
        given{
            runBlocking { aboutUserInteractor.execute(scope) }
        }.willReturn(Pair(null, UserDoesNotExistException()))


        // When we call loadAboutUserInfo
        displayUserViewModel.getDisplayUserState().observeForever(aboutUserObserver)
        displayUserViewModel.loadAboutUserInfo(userId, scope)


        // Then - verify that the onChanged was called with the correct values
        verify(aboutUserObserver).onChanged(DisplayUserViewState.UserDoesNotExist())

    }

    fun getSomAboutUser(userId: Long) : DomainAboutUser {
        return DomainAboutUser(getSomeUser(userId), getSomeDomainProfessionalSummary(userId))
    }

    fun getSomeUser(userId: Long) : DomainUser {
        return DomainUser(userId, "bright", "aaaa@aaa.com", "123456")
    }

    fun getSomeDomainProfessionalSummary(userId: Long) : DomainProfessionalSummary {
        return DomainProfessionalSummary(userId, "some summary")
    }
}