package cleanarcpro.brightowusu.com.cleanarcproj

import android.app.Application
import cleanarcpro.brightowusu.com.cleanarcproj.di.components.AppComponent
import cleanarcpro.brightowusu.com.cleanarcproj.di.components.DaggerAppComponent

import cleanarcpro.brightowusu.com.cleanarcproj.di.modules.AppModule

open class MainApplication : Application() {

    private var repositoryComponent: AppComponent? = null
    lateinit var appComponent: AppComponent

    companion object {
        lateinit var SINGLETON : MainApplication
        fun getAppComponent() : AppComponent {
            return SINGLETON.appComponent
        }
    }

    override fun onCreate() {
        super.onCreate()
        SINGLETON = this
        initDagger()
    }

    open fun initDagger() {
        appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule(this)).build()
    }


}