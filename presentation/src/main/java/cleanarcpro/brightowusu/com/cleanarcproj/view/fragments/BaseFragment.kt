package cleanarcpro.brightowusu.com.cleanarcproj.view.fragments

import android.os.Bundle
import android.support.v4.app.Fragment

abstract class BaseFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initDependencies()
    }

    abstract fun initDependencies()
    //abstract fun getTagName() : String


}