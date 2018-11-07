package cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.DomainPastExperience
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.DomainPastExperiences
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.lang.IllegalStateException

@RunWith(MockitoJUnitRunner::class)
class GetPastExperienceUseCaseTest {

    @Mock
    private lateinit var userRepository : IUserRepository

    @Mock
    private lateinit var pastExpList : List<DomainPastExperience>

    private lateinit var interactor: IGetPastExperiencesInteractor
    private val FAKE_ID = 1

    @Before
    fun setup() {
        interactor = GetPastExperiencesUseCase(userRepository)
    }

    @Test
    fun should_get_past_experiences() {

        // GIVEN
        interactor.setUserId(FAKE_ID)

        // When
        Mockito.`when`(userRepository.getPastExperiences(FAKE_ID))
                .thenReturn(Observable.just(DomainPastExperiences(pastExpList)))

        val testObserver = interactor.execute().test()

        testObserver.awaitTerminalEvent()

        val onNextEvents = testObserver.values()

        val pastExperiences = onNextEvents[0]

        // Make sure onNext was called
        testObserver.assertNoErrors()

        // Then
        Assert.assertTrue(pastExperiences.pastExperiences != null)
    }

    @Test(expected = IllegalStateException::class)
    fun should_throw_error_when_no_id_set() {
        // GIVEN
        interactor.execute().test()
    }

}