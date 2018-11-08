package cleanarcpro.brightowusu.com.cleanarcproj.data.mockservertests.usercv

import android.support.test.InstrumentationRegistry
import cleanarcpro.brightowusu.com.cleanarcproj.data.TestDependencies
import cleanarcpro.brightowusu.com.cleanarcproj.data.mockservertests.fakeserver.FakeServer
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import org.junit.After
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

class GetTopicsTest : FakeServer() {

    @Inject
    lateinit var userRepository: IUserRepository
    private val FAKE_USER_ID = 1

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
    fun should_get_topics() {
        val testObserver = userRepository.getTopicsOfKnowladge(FAKE_USER_ID).test()

        testObserver.awaitTerminalEvent()  // wait for the response

        val onNextEvents = testObserver.values()

        val topicsOfKnowladge = onNextEvents[0]

        // Make sure onNext was called
        testObserver.assertNoErrors()

        assert(topicsOfKnowladge.topicsOfKnowladge.size > 0)
        assert(topicsOfKnowladge.topicsOfKnowladge[0].topic.equals("asdadas") )
    }

}