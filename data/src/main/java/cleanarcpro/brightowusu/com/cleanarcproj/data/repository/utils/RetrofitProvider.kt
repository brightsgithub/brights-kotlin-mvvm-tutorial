package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.utils

import cleanarcpro.brightowusu.com.cleanarcproj.data.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * For testing purposes, this is defined in a single place, which will be used by Dagger and mocked
 * tests. This serves as a single place for the setup.
 * This is a work around since injecting dependencies into androidTest class is difficult.
 *
 * Created by Bright Owusu-Amankwaa
 */
class RetrofitProvider {

    companion object {
        fun providesRetrofit(okHttpClient: OkHttpClient) : Retrofit {

            return Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BuildConfig.BASE_URL)
                    .client(okHttpClient)
                    .build()
        }
    }
}