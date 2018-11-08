package cleanarcpro.brightowusu.com.cleanarcproj

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import cleanarcpro.brightowusu.com.cleanarcproj.data.TestDependencies
import cleanarcpro.brightowusu.com.cleanarcproj.data.mockservertests.fakeserver.FakeServer
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.mappers.MapDomainUserDetailsToUI
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.mappers.MapEntityUserDetailsToDomain
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.EntityUser
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainUser
import cleanarcpro.brightowusu.com.cleanarcproj.models.UIUser
import cleanarcpro.brightowusu.com.cleanarcproj.view.activities.ActivityHome
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FragmentHomeEspressoTest : FakeServer() {

    lateinit var userRepository: IUserRepository

    val testActivityRule = ActivityTestRule(ActivityHome::class.java, true, true)


    @Rule
    fun rule() = testActivityRule

    @Before
    fun setUp() {
        //rule().activity.setFragment(LoginFragment.newInstance())

        useFakeServer()
        userRepository = TestDependencies.getConfiguredUserRepository()

    }

    @Test
    fun wrongEmailFormat_shouldDisableLoginButton() {

        val uiUser = getUser()

        onView(withId(R.id.userName)).check(matches(withText(uiUser.name)))
    }


    fun getUser() : UIUser {
        val testObserver = userRepository.getUser(1).test()

        testObserver.awaitTerminalEvent()  // wait for the response

        val onNextEvents = testObserver.values()

        return MapDomainUserDetailsToUI.transform(onNextEvents[0])
    }

}