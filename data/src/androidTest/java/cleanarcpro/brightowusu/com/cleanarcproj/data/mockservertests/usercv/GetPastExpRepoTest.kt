package cleanarcpro.brightowusu.com.cleanarcproj.data.mockservertests.usercv

import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import cleanarcpro.brightowusu.com.cleanarcproj.data.TestDependencies
import cleanarcpro.brightowusu.com.cleanarcproj.data.mockservertests.fakeserver.FakeServer
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class GetPastExpRepoTest : FakeServer() {


    lateinit var userRepository: IUserRepository
    private val FAKE_USER_ID = 1L

    @Before
    fun init() {
        useFakeServer(getInstrumentation().context)
        userRepository = TestDependencies.getConfiguredUserRepository()
    }

    @After
    fun cleanUp() {
        performCleanUp()
    }

    @Test
    fun should_get_past_experiences() = runBlocking{
        userRepository.getUser(FAKE_USER_ID) // so that the user is inserted within the DB
        val entityPastExperiences = userRepository.getPastExperiences(FAKE_USER_ID)
        assert(entityPastExperiences.pastExperiences.isNotEmpty())
        assert(entityPastExperiences.pastExperiences[0].companyName.equals("Sainsbury’s") )
    }

}