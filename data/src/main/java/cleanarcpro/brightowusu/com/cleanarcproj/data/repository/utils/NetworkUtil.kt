package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.utils

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class NetworkUtil {

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
    }
}