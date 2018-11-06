package cleanarcpro.brightowusu.com.cleanarcproj.di.modules;

import android.content.Context

import javax.inject.Singleton

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.utils.NetworkUtil;
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module(includes = [AppModule::class]) // This module depends on the AppModule for use of MyApplication
class NetworkModule {


    @Singleton
    @Provides
    fun provideOkhttpClient(
            httpLoggingInterceptor: HttpLoggingInterceptor) : OkHttpClient{
        return NetworkUtil.Companion.provideOkhttpClient( httpLoggingInterceptor)
    }

    @Singleton
    @Provides
    fun provideHttpLoggingInterceptor(context: Context) : HttpLoggingInterceptor{
        return NetworkUtil.Companion.provideHttpLoggingInterceptor()
    }

    @Provides
    @Singleton
    fun gson(): Gson {
        return NetworkUtil.Companion.gson()
    }
}
