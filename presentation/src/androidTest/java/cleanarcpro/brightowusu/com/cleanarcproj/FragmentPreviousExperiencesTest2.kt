package cleanarcpro.brightowusu.com.cleanarcproj

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.runner.AndroidJUnit4
import android.view.View
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.mappers.MapPastExperiencesDomainToUI
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.UIPastExperiences
import cleanarcpro.brightowusu.com.cleanarcproj.utils.AppNavigationUtil
import org.hamcrest.CoreMatchers.allOf
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
class FragmentPreviousExperiencesTest2 : BaseUITest() {

    @Before
    fun setUp() {
        init()
    }

    @After
    fun tearDown() {
        cleanUp()
    }
    @Test
    fun make_sure_list_is_displayed() {
        val pastExperiences = getPastexperiences()
        launchActivity()

        // sets the fargment we need
        AppNavigationUtil.navigateToFragmentPreviousExperiences(testActivityRule.activity, 1)

        // Check that we can see the view
        val recyclerView = onView(
                allOf<View>(ViewMatchers.withId(R.id.previousExpRecyclerView), isDisplayed()))

    }


    fun getPastexperiences() : UIPastExperiences {
        val testObserver = userRepository.getPastExperiences(1).test()

        testObserver.awaitTerminalEvent()  // wait for the response

        val onNextEvents = testObserver.values()

        return MapPastExperiencesDomainToUI.transform(onNextEvents[0])
    }
}