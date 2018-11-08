package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.utils

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * For testing purposes, this is defined in a single place, which will be used by Dagger and mocked
 * tests. This serves as a single place for the setup.
 * This is a work around since injecting dependencies into androidTest class is difficult.
 *
 * Created by Bright Owusu-Amankwaa
 */
class NetworkProvider {

    companion object {
        fun provideOkhttpClient(
                httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {

            val client = OkHttpClient.Builder()
            client.interceptors().add(httpLoggingInterceptor)
            return client.build()
        }

        fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
            val logger = HttpLoggingInterceptor.Logger { message -> Log.v("HttpLoggingInterceptor", message) }
            val interceptor = HttpLoggingInterceptor(logger)
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            return interceptor
        }



        /**
         * Provides a configured Gson object.
         * @return
         */
        fun gson(): Gson {
            val gsonBuilder = GsonBuilder()
            return gsonBuilder.create()
        }
    }
}