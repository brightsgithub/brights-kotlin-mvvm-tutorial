package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.broadcastreceivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.network.ConnectivityInterceptor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.launch

/**
 * Created by Bright Owusu-Amankwaa on 2019-08-21.
 */
class NetworkChangeReceiver(
        private val conn: ConnectivityInterceptor,
        private val connectionChannel: BroadcastChannel<Boolean>) : BroadcastReceiver() {

    @ExperimentalCoroutinesApi
    override fun onReceive(context: Context?, intent: Intent?) {
        try {
            if (conn.isOnline()) {
                Log.v("NetworkChangeReceiver", "Online Connect Intenet ")

                GlobalScope.launch {
                    //if(!connectionChannel.isClosedForSend) {
                        connectionChannel.apply {
                            send(true)
                        }
                    //}
                }

            } else {
                Log.v("NetworkChangeReceiver", "Conectivity Failure !!! ")

                GlobalScope.launch {
                    //if(!connectionChannel.isClosedForSend) {
                        connectionChannel.apply {
                            send(false)
                        }
                    //}
                }

            }
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }

    }
}