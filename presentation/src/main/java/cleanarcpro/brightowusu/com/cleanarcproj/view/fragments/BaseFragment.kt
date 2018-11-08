package cleanarcpro.brightowusu.com.cleanarcproj.view.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View

abstract class BaseFragment : Fragment() {

    protected lateinit var baseFragmentCallBack: BaseFragmentCallBack

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDependencies()
    }

    abstract fun initDependencies()

    interface BaseFragmentCallBack {
        fun showFab()
        fun hideFab()
        fun showUpButton()
        fun hideUpButton()
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

    protected open fun showLoadingState(view : View) {
        if (isRemoving)
            return
        view.visibility = View.VISIBLE
    }

    protected open fun hideLoadingState(view : View) {
        if (isRemoving)
            return
        view.visibility = View.GONE
    }

}