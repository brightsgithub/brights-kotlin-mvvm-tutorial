package cleanarcpro.brightowusu.com.cleanarcproj.di.modules;

import android.content.Context

import javax.inject.Singleton

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.utils.NetworkProvider;
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
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
            httpLoggingInterceptor: HttpLoggingInterceptor) : OkHttpClient{
        return NetworkProvider.provideOkhttpClient( httpLoggingInterceptor)
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(context: Context) : HttpLoggingInterceptor{
        return NetworkProvider.provideHttpLoggingInterceptor()
    }

    @Provides
    @Singleton
    fun gson(): Gson {
        return NetworkProvider.gson()
    }
}
