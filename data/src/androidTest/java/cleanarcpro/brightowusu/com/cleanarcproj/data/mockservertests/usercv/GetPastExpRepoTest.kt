package cleanarcpro.brightowusu.com.cleanarcproj.data.mockservertests.usercv
import android.support.test.InstrumentationRegistry.getInstrumentation
import cleanarcpro.brightowusu.com.cleanarcproj.data.TestDependencies
import cleanarcpro.brightowusu.com.cleanarcproj.data.mockservertests.fakeserver.FakeServer
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import org.junit.After
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

class GetPastExpRepoTest : FakeServer() {

    @Inject
    lateinit var userRepository: IUserRepository
    private val FAKE_USER_ID = 1

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
    fun should_get_past_experiences() {
        val testObserver = userRepository.getPastExperiences(FAKE_USER_ID).test()

        testObserver.awaitTerminalEvent()  // wait for the response

        val onNextEvents = testObserver.values()

        val entityPastExperiences = onNextEvents[0]

        // Make sure onNext was called
        testObserver.assertNoErrors()

        assert(entityPastExperiences.pastExperiences.size > 0)
        assert(entityPastExperiences.pastExperiences[0].companyName.equals("Sainsbury’s") )
    }

}