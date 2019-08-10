package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.network

import okhttp3.Interceptor

/**
 * Created by Bright Owusu-Amankwaa on 2019-08-19.
 */
interface ConnectivityInterceptor : Interceptor {
    fun isOnline() : Boolean
}