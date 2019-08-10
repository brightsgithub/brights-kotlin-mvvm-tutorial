package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.util.Log
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.broadcastreceivers.NetworkChangeReceiver
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.network.ConnectivityInterceptor
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.network.ConnectivityInterceptorImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
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
                httpLoggingInterceptor: HttpLoggingInterceptor,
                connectivityInterceptor: ConnectivityInterceptor

        ): OkHttpClient {

            val client = OkHttpClient.Builder()
            client.interceptors().add(httpLoggingInterceptor)
            client.interceptors().add(connectivityInterceptor)
            return client.build()
        }

        fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
            val logger = HttpLoggingInterceptor.Logger { message -> Log.v("HttpLoggingInterceptor", message) }
            val interceptor = HttpLoggingInterceptor(logger)
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            return interceptor
        }

        fun provideConnectivityInterceptor(context: Context) : ConnectivityInterceptor {
            return ConnectivityInterceptorImpl(context)
        }

        /**
         * Provides a configured Gson object.
         * @return
         */
        fun gson(): Gson {
            val gsonBuilder = GsonBuilder()
            return gsonBuilder.create()
        }


        @ExperimentalCoroutinesApi
        fun provideNetworkChangeReceiver(
                conn: ConnectivityInterceptor,
                broadcastChannel: BroadcastChannel<Boolean>) : BroadcastReceiver {
            return NetworkChangeReceiver(conn, broadcastChannel)
        }

        fun provideNetworkConnectionChannel() : BroadcastChannel<Boolean> {
            return BroadcastChannel<Boolean>( 1 )
        }
    }
}