package cleanarcpro.brightowusu.com.cleanarcproj.utils

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import cleanarcpro.brightowusu.com.cleanarcproj.R
import cleanarcpro.brightowusu.com.cleanarcproj.utils.Constants.Companion.HOME_FRAG_TAG_NAME
import cleanarcpro.brightowusu.com.cleanarcproj.utils.Constants.Companion.PREV_EXP_TAG_NAME
import cleanarcpro.brightowusu.com.cleanarcproj.utils.Constants.Companion.USER_ID_EXTRA
import cleanarcpro.brightowusu.com.cleanarcproj.view.fragments.FragmentHome
import cleanarcpro.brightowusu.com.cleanarcproj.view.fragments.FragmentPreviousExperiences

class AppNavigationUtil {

    companion object {

        fun loadHomeFragment(
                activity: AppCompatActivity,
                userId: Int) {

            if(doesFragmentExist(HOME_FRAG_TAG_NAME, activity)) {
                return
            }

            val bundle = Bundle()
            bundle.putInt(USER_ID_EXTRA, userId)

            val fragment = FragmentHome()
            addFragmentToView(
                    getFragmentTransaction(activity),
                    fragment,
                    HOME_FRAG_TAG_NAME,
                    bundle)
        }

        fun loadFragmentPreviousExperiences(
                activity: AppCompatActivity,
                userId: Int) {

            if(doesFragmentExist(PREV_EXP_TAG_NAME, activity)) {
                return
            }

            val bundle = Bundle()
            bundle.putInt(USER_ID_EXTRA, userId)

            val fragment
                    =
                    FragmentPreviousExperiences()
            addFragmentToView(
                    getFragmentTransaction(activity),
                    fragment,
                    PREV_EXP_TAG_NAME,
                    bundle)
        }

        private fun doesFragmentExist(tagName : String, activity: AppCompatActivity): Boolean {
            val fm = activity.supportFragmentManager
            if(fm.findFragmentByTag(tagName) == null) {
                return false
            }
            return true
        }

        private fun addFragmentToView(
                ft: FragmentTransaction,
                f: Fragment,
                tag: String) {
            addFragmentToView(ft, f, tag, null)
        }

        private fun addFragmentToView(
                ft: FragmentTransaction,
                f: Fragment,
                tag: String,
                bundle: Bundle?) {

            if (bundle != null) {
                f.arguments = bundle
            }

            ft.replace(R.id.fragment_container, f, tag)
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            ft.addToBackStack(null)
            ft.commit()
        }

        private fun getFragmentTransaction(activity: AppCompatActivity): FragmentTransaction {
            return activity.supportFragmentManager.beginTransaction()
        }
    }
}