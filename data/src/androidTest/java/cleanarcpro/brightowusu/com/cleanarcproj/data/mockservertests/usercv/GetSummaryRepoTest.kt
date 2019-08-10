package cleanarcpro.brightowusu.com.cleanarcproj.data.mockservertests.usercv


import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import cleanarcpro.brightowusu.com.cleanarcproj.data.TestDependencies
import cleanarcpro.brightowusu.com.cleanarcproj.data.mockservertests.fakeserver.FakeServer
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class GetSummaryRepoTest : FakeServer(){

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
    fun should_get_summary()  = runBlocking {
        userRepository.getUser(FAKE_USER_ID) // so that the user is inserted within the DB
        val professionalSummary = userRepository.getProfessionalSummary(FAKE_USER_ID)
        assert(professionalSummary.professionalSummary.startsWith("I am an experienced Java developer with"))
    }

}