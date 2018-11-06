package cleanarcpro.brightowusu.com.cleanarcproj.data.mockservertests.usercv

import cleanarcpro.brightowusu.com.cleanarcproj.data.TestDependencies
import cleanarcpro.brightowusu.com.cleanarcproj.data.mockservertests.fakeserver.FakeServer
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import org.junit.After
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

class PastExperiencesRepositoryTest : FakeServer(){

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
    fun should_get_past_experiences() {

    }

}