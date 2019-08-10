package cleanarcpro.brightowusu.com.cleanarcproj.data.mockservertests.usercv

import androidx.test.platform.app.InstrumentationRegistry
import cleanarcpro.brightowusu.com.cleanarcproj.data.TestDependencies
import cleanarcpro.brightowusu.com.cleanarcproj.data.mockservertests.fakeserver.FakeServer
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import junit.framework.Assert.assertFalse
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test

class GetUserDetailsRepositoryTest : FakeServer(){


    //@Inject
    lateinit var userRepository: IUserRepository
    private val FAKE_USER_ID = 1L

    @Before
    fun init() {
        useFakeServer(InstrumentationRegistry.getInstrumentation().context)
        userRepository = TestDependencies.getConfiguredUserRepository()
    }

    @After
    fun cleanUp() {
        performCleanUp()
    }

    @Test
    fun should_get_user() = runBlocking {

        val entityUserDetails = userRepository.getUser(FAKE_USER_ID)

        assert("Bright Owusu-Amankwaa" == entityUserDetails.name)
        assert("07402244442" == entityUserDetails.phone)
        assert("brightsCodeSimple@gmail.com" == entityUserDetails.email)
    }

    @Test
    fun should_show_error_when_ids_dont_match() = runBlocking{

    val entityUserDetails = userRepository.getUser(FAKE_USER_ID)

        assertFalse(1000L == entityUserDetails.userId)
    }
}