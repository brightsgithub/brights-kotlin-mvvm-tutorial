package cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors

import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainUser
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.junit.Assert.*


@RunWith(MockitoJUnitRunner::class)
class GetUserUseCaseTest {

    @Mock
    private lateinit var userRepository : IUserRepository
    private lateinit var interactor: IGetUserInteractor
    private val FAKE_ID = 1L

    @Before
    fun setup() {
        interactor = GetUserUseCase(userRepository)
    }

    @Test
    fun should_get_user_details() = runBlocking {

        // GIVEN
        interactor.setUserId(FAKE_ID)

        given{runBlocking {userRepository.getUser(FAKE_ID)}}.willReturn(DomainUser(FAKE_ID,"","",""))


        val resultPair = interactor.execute(this)

        // Then
        assertNotNull(resultPair.first)
        assertTrue(resultPair.first?.userId!! == FAKE_ID)
    }

    @Test(expected = IllegalStateException::class)
    fun should_throw_error_when_no_id_set() = runBlocking {
        val resultPair = interactor.execute(this)

        if(resultPair.second != null) {
            throw resultPair.second!!
        }
    }

}