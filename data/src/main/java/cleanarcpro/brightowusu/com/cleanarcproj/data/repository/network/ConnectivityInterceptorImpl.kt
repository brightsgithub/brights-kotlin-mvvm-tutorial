package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.network


import android.content.Context
import android.net.ConnectivityManager
import cleanarcpro.brightowusu.com.cleanarcproj.domain.exceptions.NoConnectivityConnection
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterceptorImpl(context: Context) : ConnectivityInterceptor {

    private val appContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {

        if(!isOnline()) {
            throw NoConnectivityConnection()
        }

        return chain.proceed(chain.request())

    }

    override fun isOnline() : Boolean {
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE)
        as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}