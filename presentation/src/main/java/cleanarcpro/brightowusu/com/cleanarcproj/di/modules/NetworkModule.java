package cleanarcpro.brightowusu.com.cleanarcproj.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.utils.NetworkUtil;
import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

@Module(includes = AppModule.class) // This module depends on the AppModule for use of MyApplication
public class NetworkModule {


    @Singleton
    @Provides
    public OkHttpClient provideOkhttpClient(
            HttpLoggingInterceptor httpLoggingInterceptor) {
        return NetworkUtil.Companion.provideOkhttpClient( httpLoggingInterceptor);
    }

    @Singleton
    @Provides
    public HttpLoggingInterceptor provideHttpLoggingInterceptor(Context context) {
        return NetworkUtil.Companion.provideHttpLoggingInterceptor();
    }

}
