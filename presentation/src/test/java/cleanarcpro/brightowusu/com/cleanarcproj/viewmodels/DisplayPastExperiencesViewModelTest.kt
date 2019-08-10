package cleanarcpro.brightowusu.com.cleanarcproj.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.mappers.MapPastExperiencesDomainToUI
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.DomainPastExperience
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.DomainPastExperiences
import cleanarcpro.brightowusu.com.cleanarcproj.domain.exceptions.PastExperiencesDoesNotExistException
import cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors.IGetPastExperiencesInteractor
import cleanarcpro.brightowusu.com.cleanarcproj.viewmodels.states.PastExperiencesViewState
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Bright Owusu-Amankwaa on 2019-08-31.
 */
@RunWith(MockitoJUnitRunner::class)
class DisplayPastExperiencesViewModelTest {

    @Rule @JvmField
    var instantExecutorRule = InstantTaskExecutorRule() //Very very important

    @Mock
    lateinit var pastExperiencesInteractor: IGetPastExperiencesInteractor

    @Mock
    lateinit var pastExpObserver: Observer<PastExperiencesViewState>

    lateinit var pastExperiencesViewModel: DisplayPastExperiencesViewModel

    @Before
    fun setUp() {
        pastExperiencesViewModel = DisplayPastExperiencesViewModel()
        pastExperiencesViewModel.pastExperienceInteractor = pastExperiencesInteractor
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testPastExperiencesAreDisplayed() = runBlockingTest {
        val userId = 1L
        val scope = this
        val domainPastExp = createPastExperiences(10)

        given{
            runBlocking { pastExperiencesInteractor.execute(scope) }
        }.willReturn(Pair(domainPastExp, null))

        pastExperiencesViewModel.getDisplayPastExperiencesViewState().observeForever(pastExpObserver)
        pastExperiencesViewModel.loadPastExperiences(userId,scope)

        val uiPastExp = MapPastExperiencesDomainToUI.transform(domainPastExp)

        verify(pastExpObserver).onChanged(PastExperiencesViewState.Success(uiPastExp))
        verify(pastExpObserver).onChanged(PastExperiencesViewState.PastExperiencesExists())
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testPastExperiencesAreNotDisplayed() = runBlockingTest {
        val userId = 1L
        val scope = this

        given{
            runBlocking { pastExperiencesInteractor.execute(scope) }
        }.willReturn(Pair(null, PastExperiencesDoesNotExistException()))

        pastExperiencesViewModel.getDisplayPastExperiencesViewState().observeForever(pastExpObserver)
        pastExperiencesViewModel.loadPastExperiences(userId,scope)

        verify(pastExpObserver).onChanged(PastExperiencesViewState.PastExperiencesDoesNotExist())
    }

    private fun createPastExperiences(size: Int): DomainPastExperiences {

        val pastExpList = ArrayList<DomainPastExperience>(size)
        for(i in 0..size) {
            pastExpList.add(DomainPastExperience(
                    i.toLong(),
                    "companyName_$i",
                    "roleName_$i",
                    "datesStart_$i",
                    "datesEnd_$i",
                    "responsibilities_$i",
                    "companyLogo_$i",
                    "techUsed$i"))
        }

        return DomainPastExperiences(pastExpList)
    }

}