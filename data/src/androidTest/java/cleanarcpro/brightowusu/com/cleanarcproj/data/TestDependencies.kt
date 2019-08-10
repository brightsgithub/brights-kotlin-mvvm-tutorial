package cleanarcpro.brightowusu.com.cleanarcproj.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.db.CVDatabase
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.utils.NetworkProvider
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.utils.RepositoryProvider
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.utils.RetrofitProvider
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository

class TestDependencies {

    companion object {

        fun getConfiguredUserRepository(): IUserRepository {
            val context = ApplicationProvider.getApplicationContext<Context>()
            
            val db = Room.inMemoryDatabaseBuilder(
                    context, CVDatabase::class.java).build()

            val userDao = db.userDao()
            val proSummaryDao = db.proSummaryDao()
            val pastExperienceDao = db.pastExperienceDao()

            val connectivityInterceptor = NetworkProvider.provideConnectivityInterceptor(context)
            val interceptor = NetworkProvider.provideHttpLoggingInterceptor()
            val okHttpClient = NetworkProvider.provideOkhttpClient(interceptor, connectivityInterceptor)
            val retrofit = RetrofitProvider.providesRetrofit(okHttpClient)
            return RepositoryProvider.providesUserRepository(retrofit, userDao, proSummaryDao, pastExperienceDao)
        }
    }
}