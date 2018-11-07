package cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors

import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainProfessionalSummary
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
class GetProSummaryUseCaseTest {

    @Mock
    private lateinit var userRepository : IUserRepository
    private lateinit var interactor: IGetProSummaryInteractor
    private val FAKE_ID = 1

    @Before
    fun setup() {
        interactor = GetProSummaryUseCase(userRepository)
    }

    @Test
    fun should_get_user_details() {

        // GIVEN
        interactor.setUserId(FAKE_ID)

        // When
        Mockito.`when`(userRepository.getProfessionalSummary(FAKE_ID))
                .thenReturn(Observable.just(DomainProfessionalSummary(FAKE_ID,"")))

        val testObserver = interactor.execute().test()

        testObserver.awaitTerminalEvent()

        val onNextEvents = testObserver.values()

        val summary = onNextEvents[0]

        // Make sure onNext was called
        testObserver.assertNoErrors()

        // Then
        Assert.assertTrue(summary != null)
        Assert.assertTrue(summary.userId.equals(FAKE_ID))
    }

    @Test(expected = IllegalStateException::class)
    fun should_throw_error_when_no_id_set() {

        // GIVEN

        // When
        interactor.execute().test()

    }

}