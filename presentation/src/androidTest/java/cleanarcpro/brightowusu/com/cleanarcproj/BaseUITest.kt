package cleanarcpro.brightowusu.com.cleanarcproj

import android.content.Context
import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.support.test.rule.ActivityTestRule
import cleanarcpro.brightowusu.com.cleanarcproj.data.mockservertests.fakeserver.FakeServer
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.utils.NetworkProvider
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.utils.RepositoryProvider
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.utils.RetrofitProvider
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import cleanarcpro.brightowusu.com.cleanarcproj.view.activities.ActivityHome
import org.junit.Rule

abstract class BaseUITest : FakeServer() {

    lateinit var userRepository: IUserRepository

    @get:Rule
    public var testActivityRule: ActivityTestRule<ActivityHome> = ActivityTestRule(ActivityHome::class.java, false, false)

    protected fun init() {
        useFakeServer(getContext())
        userRepository = getConfiguredUserRepository()
    }

    protected fun cleanUp() {
        performCleanUp()
    }

    private fun getContext() : Context {
        return InstrumentationRegistry.getInstrumentation().context
    }

    private fun getTargetContext() : Context{
        return InstrumentationRegistry.getInstrumentation().targetContext
    }

    protected fun launchActivity() {
        val i = Intent(getTargetContext(), ActivityHome::class.java)
        testActivityRule.launchActivity(i)
    }

    /**
     * We get a reference to the Repo so we can call mock data, instead of hardcoding
     * values which may change
     */
    fun getConfiguredUserRepository(): IUserRepository {
        val interceptor = NetworkProvider.provideHttpLoggingInterceptor()
        val okHttpClient = NetworkProvider.provideOkhttpClient(interceptor)
        val gson = NetworkProvider.gson()
        val retrofit = RetrofitProvider.providesRetrofit(okHttpClient, gson)
        return RepositoryProvider.providesUserRepository(retrofit)
    }
}