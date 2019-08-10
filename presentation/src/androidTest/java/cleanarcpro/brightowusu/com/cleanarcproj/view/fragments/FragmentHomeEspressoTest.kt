package cleanarcpro.brightowusu.com.cleanarcproj.view.fragments

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.runner.AndroidJUnit4
import cleanarcpro.brightowusu.com.cleanarcproj.BaseUITest
import cleanarcpro.brightowusu.com.cleanarcproj.R
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.mappers.MapDomainUserDetailsToUI
import cleanarcpro.brightowusu.com.cleanarcproj.models.UIUser
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * UI Testing in isolation
 * This is so that we don't have the scenario of one fragment or activity depending on another.
 * https://developer.android.com/training/testing/ui-testing/espresso-testing#intents
 * Bright Owusu - Amankwaa
 */
@RunWith(AndroidJUnit4::class)
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

    fun getUser() : UIUser = runBlocking{
        val domainUser = userRepository.getUser(1L)
        return@runBlocking MapDomainUserDetailsToUI.transform(domainUser)
    }



}