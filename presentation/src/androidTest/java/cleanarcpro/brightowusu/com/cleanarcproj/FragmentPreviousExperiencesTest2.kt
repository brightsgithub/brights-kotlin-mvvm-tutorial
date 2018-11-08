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
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

class FragmentPreviousExperiencesTest2 :FakeServer(){

    lateinit var userRepository: IUserRepository


    @get:Rule
    public var testActivityRule: ActivityTestRule<ActivityHome> = ActivityTestRule(ActivityHome::class.java, false, false)


    @Before
    fun setUp() {
        useFakeServer(InstrumentationRegistry.getInstrumentation().context) // CONTEXT!!!!!!
        userRepository = getConfiguredUserRepository()
    }

    @Test
    fun make_sure_list_is_displayed() {
        val pastExperiences = getPastexperiences()
        val i = Intent(InstrumentationRegistry.getInstrumentation().targetContext, ActivityHome::class.java)
        testActivityRule.launchActivity(i)

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

    fun getConfiguredUserRepository(): IUserRepository {
        val interceptor = NetworkUtil.provideHttpLoggingInterceptor()
        val okHttpClient = NetworkUtil.provideOkhttpClient(interceptor)
        val gson = NetworkUtil.gson()
        val retrofit = RetrofitProvider.providesRetrofit(okHttpClient, gson)
        return RepositoryProvider.providesUserRepository(retrofit)
    }

}