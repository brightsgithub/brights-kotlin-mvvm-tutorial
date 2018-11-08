package cleanarcpro.brightowusu.com.cleanarcproj.view.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View

/**
 * Things most Fragments care about
 * Created by Bright Owusu-Amankwaa
 */
abstract class BaseFragment : Fragment() {

    protected lateinit var baseFragmentCallBack: BaseFragmentCallBack

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDependencies()
    }

    /**
     * Init dagger dependencies here
     */
    abstract fun initDependencies()

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
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseFragmentCallBack) {
            baseFragmentCallBack = context
        } else {
            throw IllegalStateException("Activity must implement FragmentCurrencyListCallback")
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

}