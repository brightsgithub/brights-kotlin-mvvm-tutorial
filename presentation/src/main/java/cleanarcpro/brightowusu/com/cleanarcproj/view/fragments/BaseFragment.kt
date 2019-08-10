package cleanarcpro.brightowusu.com.cleanarcproj.view.fragments

import android.content.BroadcastReceiver
import android.content.Context
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import cleanarcpro.brightowusu.com.cleanarcproj.MainApplication
import cleanarcpro.brightowusu.com.cleanarcproj.R
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.UIPastExperience
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.consume
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.last
import kotlinx.coroutines.flow.consumeAsFlow
import kotlin.coroutines.CoroutineContext

/**
 * Things most Fragments care about
 * Created by Bright Owusu-Amankwaa
 */
abstract class BaseFragment : Fragment(), CoroutineScope {

    protected lateinit var baseFragmentCallBack: BaseFragmentCallBack
    private lateinit var connectivityReceiver: BroadcastReceiver
    private lateinit var networkBroadcastChannel: BroadcastChannel<Boolean>

    protected lateinit var job: Job
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private lateinit var subClassName: String
    private var wasConnectionLost = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subClassName = this.javaClass.simpleName
        initDependencies()
    }

    private fun initNetworkChangeReceiver() {
        connectivityReceiver = MainApplication.getNetworkChangeReceiver()
    }

    @ExperimentalCoroutinesApi
    private fun initNetworkBroadcastChannel() {
        networkBroadcastChannel = MainApplication.getNetworkBroadcastChannel()
        launch { handleNetworkChange() }
    }

    private suspend fun handleNetworkChange() {

        networkBroadcastChannel.consumeEach {
            isConnected -> Boolean

            Log.v("networkBroadcastChannel", "$subClassName isConnected $isConnected")

            if(isConnected) {
                showConnectionSnackbar()
                wasConnectionLost = false
                handleDeviceHasConnection()
            } else {
                wasConnectionLost = true
                showNoConnectionSnackbar()
                handleDeviceHasNoConnection()
            }
        }
    }

    /**
     * 1) Get a handle on the job for this coroutine scope
     * 2) Register the Broadcast receiver to listen for network changes
     * 3) Create the network channel which will listen in for network changes from the receiver
     */
    @ExperimentalCoroutinesApi
    override fun onStart() {
        super.onStart()
        job = Job()
        initNetworkChangeReceiver()
        initNetworkBroadcastChannel()
        activity?.registerReceiver(connectivityReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))
    }

    /**
     * 1) Cancel any jobs
     * 2) Unregister the broadcast receiver Fragment from this fragment
     */
    override fun onStop() {
        super.onStop()
        job.cancel()
        activity?.unregisterReceiver(connectivityReceiver)
    }

    /**
     * Init dagger dependencies here
     */
    abstract fun initDependencies()

    abstract fun onBackPressedShouldWeCloseActivity() : Boolean

    abstract fun handleDeviceHasConnection()

    abstract fun handleDeviceHasNoConnection()

    interface BaseFragmentCallBack {
        /**
         * Shows the FloatingActionButton .
         */
        fun showFab()

        /**
         * Hides the FloatingActionButton.
         */
        fun hideFab()

        /**
         * Shows the Up navigation button.
         */
        fun showUpButton()

        /**
         * Hides the Up navigation button.
         */
        fun hideUpButton()

        /**
         * Sets the toolbar title.
         */
        fun setToolBarTitle(title: String)

        /**
         * Navigate to detail activity
         */
        fun navigateToFragmentPreviousExperienceDetail(item: UIPastExperience)

        /**
         * show snack bar
         */
        fun showSnackbar(
                msg: String,
                length: Int,
                bgHexColor: String? = null,
                textHexColor: String? = null)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseFragmentCallBack) {
            baseFragmentCallBack = context
        } else {
            throw IllegalStateException("Activity must implement BaseFragmentCallBack")
        }
    }

    /**
     * Show loading state.
     */
    protected open fun showLoadingState(view : View) {
        if (isRemoving)
            return
        view.visibility = View.VISIBLE
    }

    /**
     * Hide loading state
     */
    protected open fun hideLoadingState(view : View) {
        if (isRemoving)
            return
        view.visibility = View.GONE
    }

    private fun showNoConnectionSnackbar() {
        showSnackbar(getString(R.string.no_connection), Snackbar.LENGTH_INDEFINITE)
    }

    private fun showConnectionSnackbar() {
        if(wasConnectionLost) {
            showSnackbar(
                    getString(R.string.back_online),
                    Snackbar.LENGTH_SHORT,
                    bgHexColor ="#32CD32"
                    )

        }
    }

    fun showSnackbar(msg: String, length: Int, bgHexColor: String? = null, textHexColor: String? = null) {
        baseFragmentCallBack.showSnackbar(msg, length, bgHexColor, textHexColor)
    }

}