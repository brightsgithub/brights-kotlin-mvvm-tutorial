package cleanarcpro.brightowusu.com.cleanarcproj.data.mockservertests.usercv

import cleanarcpro.brightowusu.com.cleanarcproj.data.TestDependencies
import cleanarcpro.brightowusu.com.cleanarcproj.data.mockservertests.fakeserver.FakeServer
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import junit.framework.Assert.assertFalse
import org.junit.After
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

class UserDetailsRepositoryTest : FakeServer(){


    @Inject
    lateinit var userRepository: IUserRepository
    private val FAKE_USER_ID = 1

    @Before
    fun init() {
        useFakeServer()
        userRepository = TestDependencies.getConfiguredUserRepository()
    }

    @After
    fun cleanUp() {
        performCleanUp()
    }

    @Test
    fun should_get_user() {

        val testObserver = userRepository.getUser(FAKE_USER_ID).test()

        testObserver.awaitTerminalEvent()  // wait for the response

        val onNextEvents = testObserver.values()

        val entityUserDetails = onNextEvents[0]

        // Make sure onNext was called
        testObserver.assertNoErrors()

        assert("bright".equals(entityUserDetails.name))
    }

    @Test
    fun should_show_error_when_ids_dont_match() {

        val testObserver = userRepository.getUser(FAKE_USER_ID).test()

        testObserver.awaitTerminalEvent()  // wait for the response

        val onNextEvents = testObserver.values()

        val entityUserDetails = onNextEvents[0]

        // Make sure onNext was called
        testObserver.assertNoErrors()

        assertFalse(1000.equals(entityUserDetails.userId))
    }
}