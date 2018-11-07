package cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors

import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainUser
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.junit.Assert.*
import java.lang.IllegalStateException


@RunWith(MockitoJUnitRunner::class)
class GetUserUseCaseTest {

    @Mock
    private lateinit var userRepository : IUserRepository
    private lateinit var getUserInteractor: IGetUserInteractor
    private val FAKE_ID = 1

    @Before
    fun setup() {
        getUserInteractor = GetUserUseCase(userRepository)
    }

    @Test
    fun should_get_user_details() {

        // GIVEN
        getUserInteractor.setUserId(FAKE_ID)

        // When
        Mockito.`when`(userRepository.getUser(FAKE_ID))
                .thenReturn(Observable.just(DomainUser(FAKE_ID,"","","")))

        val testObserver = getUserInteractor.execute().test()

        testObserver.awaitTerminalEvent()

        val onNextEvents = testObserver.values()

        val domainUser = onNextEvents[0]

        // Make sure onNext was called
        testObserver.assertNoErrors()

        // Then
        assertTrue(domainUser != null)
        assertTrue(domainUser.userId.equals(FAKE_ID))
    }

    @Test(expected = IllegalStateException::class)
    fun should_throw_error_when_no_id_set() {

        // GIVEN

        // When
        Mockito.`when`(userRepository.getUser(FAKE_ID))
                .thenReturn(Observable.just(DomainUser(FAKE_ID,"","","")))

        getUserInteractor.execute().test()

    }

}