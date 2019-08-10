package cleanarcpro.brightowusu.com.cleanarcproj

import android.app.Application
import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Build
import cleanarcpro.brightowusu.com.cleanarcproj.BuildConfig.DEBUG
import cleanarcpro.brightowusu.com.cleanarcproj.di.components.AppComponent
import cleanarcpro.brightowusu.com.cleanarcproj.di.components.DaggerAppComponent

import cleanarcpro.brightowusu.com.cleanarcproj.di.modules.AppModule
import com.facebook.stetho.Stetho
import com.squareup.picasso.Picasso
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel

/**
 * Created by Bright Owusu-Amankwaa
 */
open class MainApplication : Application() {

    private var repositoryComponent: AppComponent? = null
    lateinit var appComponent: AppComponent

    companion object {
        lateinit var SINGLETON : MainApplication
        fun getAppComponent() : AppComponent {
            return SINGLETON.appComponent
        }

        fun getPicasso() : Picasso{
            return SINGLETON.appComponent.getPicasso()
        }

        fun getNetworkChangeReceiver() : BroadcastReceiver {
            return SINGLETON.appComponent.getNetworkChangeReceiver()
        }

        @ExperimentalCoroutinesApi
        fun getNetworkBroadcastChannel(): BroadcastChannel<Boolean> {
            return SINGLETON.appComponent.getNetworkBroadcastChannel()
        }
    }

    override fun onCreate() {
        super.onCreate()
        SINGLETON = this
        initSteho()
        initDagger()
    }

    open fun initDagger() {
        appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }

    private fun initSteho() {
        if(DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }


}