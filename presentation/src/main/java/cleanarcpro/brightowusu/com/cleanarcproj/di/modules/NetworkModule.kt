package cleanarcpro.brightowusu.com.cleanarcproj.di.modules;

import android.content.BroadcastReceiver
import android.content.Context
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.network.ConnectivityInterceptor

import javax.inject.Singleton

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.utils.NetworkProvider;
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Responsible for Network config.
 *
 * Created by Bright Owusu-Amankwaa
 */
@Module(includes = [AppModule::class]) // This module depends on the AppModule for use of MyApplication
class NetworkModule {


    @Singleton
    @Provides
    fun provideOkhttpClient(
            httpLoggingInterceptor: HttpLoggingInterceptor,
            connectivityInterceptor: ConnectivityInterceptor) : OkHttpClient{
        return NetworkProvider.provideOkhttpClient(httpLoggingInterceptor, connectivityInterceptor)
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(context: Context) : HttpLoggingInterceptor{
        return NetworkProvider.provideHttpLoggingInterceptor()
    }

    @Singleton
    @Provides
    fun provideConnectivityInterceptor(context: Context) : ConnectivityInterceptor {
        return NetworkProvider.provideConnectivityInterceptor(context)
    }

    @ExperimentalCoroutinesApi
    @Singleton
    @Provides
    fun provideNetworkChangeReceiver(
            conn: ConnectivityInterceptor,
            broadcastChannel: BroadcastChannel<Boolean>) : BroadcastReceiver {
        return NetworkProvider.provideNetworkChangeReceiver(conn, broadcastChannel)
    }

    @ExperimentalCoroutinesApi
    @Singleton
    @Provides
    fun provideNetworkConnectionChannel() : BroadcastChannel<Boolean> {
        return BroadcastChannel( 1 )
    }

    @Provides
    @Singleton
    fun gson(): Gson {
        return NetworkProvider.gson()
    }
}
