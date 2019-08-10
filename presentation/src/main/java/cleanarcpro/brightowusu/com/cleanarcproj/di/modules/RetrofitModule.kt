package cleanarcpro.brightowusu.com.cleanarcproj.di.modules

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.utils.RetrofitProvider
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Retrofit clients.
 *
 * Created by Bright Owusu-Amankwaa
 */
@Module(includes = [AppModule::class])
class RetrofitModule {


    /**
     * Provides a configured Retrofit client
     */
    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient) : Retrofit {
        return RetrofitProvider.providesRetrofit(okHttpClient)
    }
}