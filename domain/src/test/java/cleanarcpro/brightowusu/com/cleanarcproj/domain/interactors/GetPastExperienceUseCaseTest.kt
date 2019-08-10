package cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.DomainPastExperience
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.DomainPastExperiences
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
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
class GetPastExperienceUseCaseTest {

    @Mock
    private lateinit var userRepository : IUserRepository

    @Mock
    private lateinit var pastExpList : List<DomainPastExperience>

    private lateinit var interactor: IGetPastExperiencesInteractor
    private val FAKE_ID = 1L

    @Before
    fun setup() {
        interactor = GetPastExperiencesUseCase(userRepository)
    }

    @Test
    fun should_get_past_experiences() = runBlocking {

        // GIVEN
        interactor.setUserId(FAKE_ID)

        // When
        given{
            runBlocking { userRepository.getPastExperiences(FAKE_ID) }
        }.willReturn(DomainPastExperiences(pastExpList))

        val resultPair = interactor.execute(this)

        // Then
        Assert.assertNotNull(resultPair.first)
    }

    @Test(expected = IllegalStateException::class)
    fun should_throw_error_when_no_id_set() = runBlocking {
        val resultPair = interactor.execute(this)

        if(resultPair.second != null) {
            throw resultPair.second!!
        }
    }

}