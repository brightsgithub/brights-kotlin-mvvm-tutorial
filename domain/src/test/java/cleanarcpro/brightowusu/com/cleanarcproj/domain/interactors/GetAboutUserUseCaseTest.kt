package cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors

import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainProfessionalSummary
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainUser
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * Created by Bright Owusu-Amankwaa
 */
@Ignore
@RunWith(MockitoJUnitRunner::class)
class GetAboutUserUseCaseTest {

    @Mock
    private lateinit var userRepository : IUserRepository

    @Mock
    private lateinit var userInteractor: IGetUserInteractor

    @Mock
    private lateinit var summaryInteractor: IGetProSummaryInteractor

    private lateinit var interactor: IGetAboutUserInteractor

    private val FAKE_ID = 1

    @Before
    fun setup() {
        interactor = GetAboutUserUseCase(summaryInteractor, userInteractor)
    }

    @Test
    fun should_get_about_user() {

        // GIVEN
        interactor.setUserId(FAKE_ID)

        // When
        Mockito.`when`(userRepository.getProfessionalSummary(FAKE_ID))
                .thenReturn(Observable.just(DomainProfessionalSummary(FAKE_ID,"")))

        Mockito.`when`(userRepository.getUser(FAKE_ID))
                .thenReturn(Observable.just(DomainUser(FAKE_ID,"","","")))

        val testObserver = interactor.execute().test()

        testObserver.awaitTerminalEvent()

        val onNextEvents = testObserver.values()

        val result = onNextEvents[0]

        // Make sure onNext was called
        testObserver.assertNoErrors()

        // Then
        Assert.assertTrue(result.domainSummary != null)
        Assert.assertTrue(result.domainUser != null)
    }

    @Test(expected = IllegalStateException::class)
    fun should_throw_error_when_no_id_set() {
        // GIVEN
        interactor.execute().test()
    }
}