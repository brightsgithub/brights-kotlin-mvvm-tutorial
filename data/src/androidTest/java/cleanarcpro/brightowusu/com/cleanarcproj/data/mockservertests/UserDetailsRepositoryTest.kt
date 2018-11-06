package cleanarcpro.brightowusu.com.cleanarcproj.data.mockservertests

import cleanarcpro.brightowusu.com.cleanarcproj.data.mockservertests.fakeserver.FakeServer
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.EntityUser
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

class UserDetailsRepositoryTest : FakeServer(){


    @Inject
    lateinit var userRepository: IUserRepository

    @Before
    fun init() {
        useFakeServer()
    }


    @Test
    fun shouldGetNone() {

        val testObserver = userRepository.getUser().test()

        testObserver.awaitTerminalEvent()  // wait for the response

        val onNextEvents = testObserver.values()

        val entityUserDetails = onNextEvents[0]

        // Make sure onNext was called
        testObserver.assertNoErrors()


        assert("bright".equals(entityUserDetails.name))

    }
}