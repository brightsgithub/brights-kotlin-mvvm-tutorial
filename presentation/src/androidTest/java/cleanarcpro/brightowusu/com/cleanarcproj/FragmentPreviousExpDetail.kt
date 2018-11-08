package cleanarcpro.brightowusu.com.cleanarcproj

import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.runner.AndroidJUnit4
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.mappers.MapPastExperiencesDomainToUI
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.UIPastExperiences
import cleanarcpro.brightowusu.com.cleanarcproj.utils.AppNavigationUtil
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
class FragmentPreviousExpDetail : BaseUITest() {

    @Before
    fun setUp() {
        init()
    }

    @After
    fun tearDown() {
        cleanUp()
    }

    @Test
    fun make_sure_detail_is_displayed() {
        val pastExperiences = getPastexperiences()

        val pastexp = pastExperiences.pastExperiences[0]

        launchActivity()

        // sets the fargment we need
        AppNavigationUtil.navigateToFragmentPreviousExperienceDetail(testActivityRule.activity, pastexp)

        // Check that we can see the view
        Espresso.onView(ViewMatchers.withId(R.id.companyName)).check(ViewAssertions.matches(ViewMatchers.withText(pastexp.companyName)))
        Espresso.onView(ViewMatchers.withId(R.id.roleName)).check(ViewAssertions.matches(ViewMatchers.withText(pastexp.roleName)))
        Espresso.onView(ViewMatchers.withId(R.id.startDate)).check(ViewAssertions.matches(ViewMatchers.withText(pastexp.datesStart)))
        Espresso.onView(ViewMatchers.withId(R.id.endDate)).check(ViewAssertions.matches(ViewMatchers.withText(pastexp.dateEnd)))
    }


    fun getPastexperiences() : UIPastExperiences {
        val testObserver = userRepository.getPastExperiences(1).test()

        testObserver.awaitTerminalEvent()  // wait for the response

        val onNextEvents = testObserver.values()

        return MapPastExperiencesDomainToUI.transform(onNextEvents[0])
    }

}