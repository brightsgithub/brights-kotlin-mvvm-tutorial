package cleanarcpro.brightowusu.com.cleanarcproj

import android.content.Context
import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import cleanarcpro.brightowusu.com.cleanarcproj.data.mockservertests.fakeserver.FakeServer
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.mappers.MapDomainUserDetailsToUI
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.mappers.MapPastExperiencesDomainToUI
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.UIPastExperiences
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.utils.NetworkUtil
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.utils.RepositoryProvider
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.utils.RetrofitProvider
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import cleanarcpro.brightowusu.com.cleanarcproj.models.UIUser
import cleanarcpro.brightowusu.com.cleanarcproj.utils.AppNavigationUtil
import cleanarcpro.brightowusu.com.cleanarcproj.view.activities.ActivityHome
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

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