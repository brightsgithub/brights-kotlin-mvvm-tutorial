package cleanarcpro.brightowusu.com.cleanarcproj.data

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.utils.NetworkProvider
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.utils.RepositoryProvider
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.utils.RetrofitProvider
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository

class TestDependencies {

    companion object {

        fun getConfiguredUserRepository(): IUserRepository {
            val interceptor = NetworkProvider.provideHttpLoggingInterceptor()
            val okHttpClient = NetworkProvider.provideOkhttpClient(interceptor)
            val gson = NetworkProvider.gson()
            val retrofit = RetrofitProvider.providesRetrofit(okHttpClient, gson)
            return RepositoryProvider.providesUserRepository(retrofit)
        }


    }


}