package cleanarcpro.brightowusu.com.cleanarcproj.di.modules;

import android.content.Context

import javax.inject.Singleton

import dagger.Module
import dagger.Provides

@Module
class AppModule(val myApplication : Context) {


    @Provides
    @Singleton
    fun providesApplication(): Context {
        return myApplication
    }
}
