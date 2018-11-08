package cleanarcpro.brightowusu.com.cleanarcproj

import android.content.Context
import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withText
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import cleanarcpro.brightowusu.com.cleanarcproj.data.mockservertests.fakeserver.FakeServer
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.mappers.MapDomainUserDetailsToUI
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.utils.NetworkUtil
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.utils.RepositoryProvider
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.utils.RetrofitProvider
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import cleanarcpro.brightowusu.com.cleanarcproj.models.UIUser
import cleanarcpro.brightowusu.com.cleanarcproj.view.activities.ActivityHome
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FragmentHomeEspressoTest : FakeServer() {

    lateinit var userRepository: IUserRepository

    @get:Rule
    public var testActivityRule: ActivityTestRule<ActivityHome> = ActivityTestRule(ActivityHome::class.java, false, false)

    @Before
    fun setUp() {
        useFakeServer(InstrumentationRegistry.getInstrumentation().context) // CONTEXT!!!!!!
        userRepository = getConfiguredUserRepository()

    }

    @Test
    fun wrongEmailFormat_shouldDisableLoginButton() {

        val uiUser = getUser()
        val i = Intent(InstrumentationRegistry.getInstrumentation().targetContext, ActivityHome::class.java)
        testActivityRule.launchActivity(i)
        onView(withId(R.id.userName)).check(matches(withText(uiUser.name))) // TARGET CONTEXT!!!!!!
    }


    fun getUser() : UIUser {
        val testObserver = userRepository.getUser(1).test()

        testObserver.awaitTerminalEvent()  // wait for the response

        val onNextEvents = testObserver.values()

        return MapDomainUserDetailsToUI.transform(onNextEvents[0])
    }

    fun getConfiguredUserRepository(): IUserRepository {
        val interceptor = NetworkUtil.provideHttpLoggingInterceptor()
        val okHttpClient = NetworkUtil.provideOkhttpClient(interceptor)
        val gson = NetworkUtil.gson()
        val retrofit = RetrofitProvider.providesRetrofit(okHttpClient, gson)
        return RepositoryProvider.providesUserRepository(retrofit)
    }

}