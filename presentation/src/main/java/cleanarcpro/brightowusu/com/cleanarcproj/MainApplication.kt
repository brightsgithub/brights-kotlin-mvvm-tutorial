package cleanarcpro.brightowusu.com.cleanarcproj

import android.app.Application
import cleanarcpro.brightowusu.com.cleanarcproj.di.components.AppComponent
import cleanarcpro.brightowusu.com.cleanarcproj.di.components.DaggerAppComponent

import cleanarcpro.brightowusu.com.cleanarcproj.di.modules.AppModule

class MainApplication : Application() {

    private var repositoryComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()

        initDagger()
    }

    private fun initDagger() {
//        DaggerAppComponent
//                .builder()
//                .build()

//        repositoryComponent = DaggerAppComponent
//                .builder()
//                .appModule(AppModule(this))
//                .build()
//
        DaggerAppComponent
                .builder()
                .appModule(AppModule(this)).build()


    }
}