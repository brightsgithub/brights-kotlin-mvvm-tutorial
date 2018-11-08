package cleanarcpro.brightowusu.com.cleanarcproj

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.runner.AndroidJUnit4
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.mappers.MapDomainUserDetailsToUI
import cleanarcpro.brightowusu.com.cleanarcproj.models.UIUser
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
/**
 *
 */
class FragmentHomeEspressoTest : BaseUITest() {

    @Before
    fun setUp() {
        init()
    }

    @After
    fun tearDown() {
       cleanUp()
    }

    @Test
    fun should_display_user() {
        val uiUser = getUser()
        launchActivity()
        onView(withId(R.id.userName)).check(matches(withText(uiUser.name))) // TARGET CONTEXT!!!!!!
    }

    fun getUser() : UIUser {
        val testObserver = userRepository.getUser(1).test()

        testObserver.awaitTerminalEvent()  // wait for the response

        val onNextEvents = testObserver.values()

        return MapDomainUserDetailsToUI.transform(onNextEvents[0])
    }



}