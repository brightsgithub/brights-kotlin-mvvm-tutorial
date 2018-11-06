package cleanarcpro.brightowusu.com.cleanarcproj.di.modules

import cleanarcpro.brightowusu.com.cleanarcproj.BuildConfig
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class RetrofitModule {


    /**
     * Provides a configured Retrofit client
     */
    @Singleton
    @Provides
    fun providesRetrofit(
            okHttpClient: OkHttpClient,
            gson: Gson) : Retrofit{
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .build()
    }

}