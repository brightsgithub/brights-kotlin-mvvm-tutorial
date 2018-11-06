package cleanarcpro.brightowusu.com.cleanarcproj.data

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.utils.NetworkUtil
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.utils.RepositoryProvider
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.utils.RetrofitProvider
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository

class TestDependencies {

    companion object {

        fun getConfiguredUserRepository(): IUserRepository {
            val interceptor = NetworkUtil.provideHttpLoggingInterceptor()
            val okHttpClient = NetworkUtil.provideOkhttpClient(interceptor)
            val gson = NetworkUtil.gson()
            val retrofit = RetrofitProvider.providesRetrofit(okHttpClient, gson)
            return RepositoryProvider.providesUserRepository(retrofit)
        }


    }


}