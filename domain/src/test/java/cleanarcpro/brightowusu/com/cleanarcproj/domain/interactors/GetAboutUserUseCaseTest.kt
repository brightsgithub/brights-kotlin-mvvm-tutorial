package cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors

import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainProfessionalSummary
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainUser
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.runBlocking
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

    private val FAKE_ID = 1L

    @Before
    fun setup() {
        interactor = GetAboutUserUseCase(summaryInteractor, userInteractor)
    }

    @Test
    fun should_get_about_user() = runBlocking {

        // GIVEN
        interactor.setUserId(FAKE_ID)

        // When

        given {
            runBlocking { userRepository.getProfessionalSummary(FAKE_ID) }

        }.willReturn(DomainProfessionalSummary(FAKE_ID,""))


        given {
            runBlocking { userRepository.getUser(FAKE_ID) }

        }.willReturn(DomainUser(FAKE_ID,"","",""))

        val resultPair = interactor.execute(this)


        // Then
        resultPair.first?.let {
            Assert.assertTrue(it.domainSummary.equals(DomainProfessionalSummary(FAKE_ID,"")))
            Assert.assertTrue(it.domainUser.equals(DomainUser(FAKE_ID,"","","")))
        }

    }

    @Test(expected = IllegalStateException::class)
    fun should_throw_error_when_no_id_set() = runBlocking {
        val resultPair = interactor.execute(this)

        if(resultPair.second != null) {
            throw resultPair.second!!
        }
    }
}