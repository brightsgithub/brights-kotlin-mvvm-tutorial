package cleanarcpro.brightowusu.com.cleanarcproj

import android.content.Context
import android.content.Intent
import androidx.test.platform.app.InstrumentationRegistry

import androidx.test.rule.ActivityTestRule
import cleanarcpro.brightowusu.com.cleanarcproj.data.mockservertests.fakeserver.FakeServer
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.utils.DatabaseProvider
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
        val context = getTargetContext()

        // Intergration tests, so using real db
        val db = DatabaseProvider
        val userDao = db.providesUserDao(context)
        val proSummaryDao = db.providesProSummaryDao(context)
        val pastExperienceDao = db.providesPastExperienceDao(context)

        val connectivityInterceptor = NetworkProvider.provideConnectivityInterceptor(context)
        val interceptor = NetworkProvider.provideHttpLoggingInterceptor()
        val okHttpClient = NetworkProvider.provideOkhttpClient(interceptor, connectivityInterceptor)
        val retrofit = RetrofitProvider.providesRetrofit(okHttpClient)
        return RepositoryProvider.providesUserRepository(retrofit, userDao, proSummaryDao, pastExperienceDao)
    }
}