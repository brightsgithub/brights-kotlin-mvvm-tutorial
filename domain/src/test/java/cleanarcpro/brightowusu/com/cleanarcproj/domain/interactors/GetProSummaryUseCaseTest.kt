package cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors

import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainProfessionalSummary
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.lang.IllegalStateException

@RunWith(MockitoJUnitRunner::class)
class GetProSummaryUseCaseTest {

    @Mock
    private lateinit var userRepository : IUserRepository
    private lateinit var interactor: IGetProSummaryInteractor
    private val FAKE_ID = 1L

    @Before
    fun setup() {
        interactor = GetProSummaryUseCase(userRepository)
    }

    @Test
    fun should_get_user_details() = runBlocking {

        // GIVEN
        interactor.setUserId(FAKE_ID)

        // When

        given {
            runBlocking { userRepository.getProfessionalSummary(FAKE_ID) }
        }.willReturn(DomainProfessionalSummary(FAKE_ID,""))

        val resultPair = interactor.execute(this)

        // Then
        Assert.assertNotNull(resultPair.first)
        Assert.assertTrue(resultPair.first?.userId!! == FAKE_ID)
    }

    @Test(expected = IllegalStateException::class)
    fun should_throw_error_when_no_id_set() = runBlocking {
        val resultPair = interactor.execute(this)

        if(resultPair.second != null) {
            throw resultPair.second!!
        }
    }

}