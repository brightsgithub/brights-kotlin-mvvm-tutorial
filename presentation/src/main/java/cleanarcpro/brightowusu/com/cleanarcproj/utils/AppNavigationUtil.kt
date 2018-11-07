package cleanarcpro.brightowusu.com.cleanarcproj.utils

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import cleanarcpro.brightowusu.com.cleanarcproj.utils.Constants.Companion.HOME_FRAG_TAG_NAME
import cleanarcpro.brightowusu.com.cleanarcproj.utils.Constants.Companion.HOME_FRAG_USER_ID_EXTRA
import cleanarcpro.brightowusu.com.cleanarcproj.view.fragments.FragmentHome

class AppNavigationUtil {

    companion object {

        fun loadHomeFragment(
                activity: AppCompatActivity,
                frameLayoutId: Int,
                userId: Int) {

            val bundle = Bundle()
            bundle.putInt(HOME_FRAG_USER_ID_EXTRA, userId)

            val fragment = FragmentHome()
            addFragmentToView(
                    getFragmentTransaction(activity),
                    fragment,
                    HOME_FRAG_TAG_NAME,
                    frameLayoutId,
                    bundle)
        }

        private fun addFragmentToView(
                ft: FragmentTransaction,
                f: Fragment,
                tag: String,
                frameLayoutId: Int) {
            addFragmentToView(ft, f, tag, frameLayoutId, null)
        }

        private fun addFragmentToView(
                ft: FragmentTransaction,
                f: Fragment,
                tag: String,
                frameLayoutId: Int,
                bundle: Bundle?) {

            if (bundle != null) {
                f.arguments = bundle
            }

            ft.add(frameLayoutId, f, tag)
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            ft.commit()
        }

        private fun getFragmentTransaction(activity: AppCompatActivity): FragmentTransaction {
            return activity.supportFragmentManager.beginTransaction()
        }
    }
}