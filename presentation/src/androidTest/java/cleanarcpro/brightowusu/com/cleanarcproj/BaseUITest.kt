package cleanarcpro.brightowusu.com.cleanarcproj

import android.app.Instrumentation
import android.content.Context
import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import cleanarcpro.brightowusu.com.cleanarcproj.data.mockservertests.fakeserver.FakeServer
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.utils.NetworkUtil
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.utils.RepositoryProvider
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.utils.RetrofitProvider
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import cleanarcpro.brightowusu.com.cleanarcproj.view.activities.ActivityHome
import org.junit.Rule

abstract class BaseUITest : FakeServer() {

    lateinit var userRepository: IUserRepository

    @get:Rule
    public var testActivityRule: ActivityTestRule<ActivityHome> = ActivityTestRule(ActivityHome::class.java, false, false)

    fun init() {
        useFakeServer(getContext())
        userRepository = getConfiguredUserRepository()
    }

    fun cleanUp() {
        performCleanUp()
    }

    fun getContext() : Context {
        return InstrumentationRegistry.getInstrumentation().context
    }

    fun getTargetContext() : Context{
        return InstrumentationRegistry.getInstrumentation().targetContext
    }

    fun launchActivity() {
        val i = Intent(getTargetContext(), ActivityHome::class.java)
        testActivityRule.launchActivity(i)
    }

    fun getConfiguredUserRepository(): IUserRepository {
        val interceptor = NetworkUtil.provideHttpLoggingInterceptor()
        val okHttpClient = NetworkUtil.provideOkhttpClient(interceptor)
        val gson = NetworkUtil.gson()
        val retrofit = RetrofitProvider.providesRetrofit(okHttpClient, gson)
        return RepositoryProvider.providesUserRepository(retrofit)
    }
}