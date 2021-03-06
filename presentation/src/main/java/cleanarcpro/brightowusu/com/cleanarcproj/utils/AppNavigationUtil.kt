package cleanarcpro.brightowusu.com.cleanarcproj.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import androidx.appcompat.app.AppCompatActivity
import cleanarcpro.brightowusu.com.cleanarcproj.R
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.UIPastExperience
import cleanarcpro.brightowusu.com.cleanarcproj.utils.Constants.Companion.HOME_FRAG_TAG_NAME
import cleanarcpro.brightowusu.com.cleanarcproj.utils.Constants.Companion.PREV_EXP_DET_TAG_NAME
import cleanarcpro.brightowusu.com.cleanarcproj.utils.Constants.Companion.PREV_EXP_EXTRA
import cleanarcpro.brightowusu.com.cleanarcproj.utils.Constants.Companion.PREV_EXP_TAG_NAME
import cleanarcpro.brightowusu.com.cleanarcproj.utils.Constants.Companion.USER_ID_EXTRA
import cleanarcpro.brightowusu.com.cleanarcproj.view.fragments.FragmentHome
import cleanarcpro.brightowusu.com.cleanarcproj.view.fragments.FragmentPreviousExpDetail
import cleanarcpro.brightowusu.com.cleanarcproj.view.fragments.FragmentPreviousExperiences

/**
 * Currently Responsible for app navigation.
 * (Would be good to soon start using the new Navigation Component)
 */
class AppNavigationUtil {

    companion object {

        fun navigateToHomeFragment(
                activity: AppCompatActivity,
                userId: Long) {

            if(doesFragmentExist(HOME_FRAG_TAG_NAME, activity)) {
                return
            }

            val bundle = Bundle()
            bundle.putLong(USER_ID_EXTRA, userId)

            val fragment = FragmentHome()
            addFragmentToView(
                    getFragmentTransaction(activity),
                    fragment,
                    HOME_FRAG_TAG_NAME,
                    bundle)
        }

        fun navigateToFragmentPreviousExperiences(
                activity: AppCompatActivity,
                userId: Long) {

            if(doesFragmentExist(PREV_EXP_TAG_NAME, activity)) {
                return
            }

            val bundle = Bundle()
            bundle.putLong(USER_ID_EXTRA, userId)

            val fragment
                    =
                    FragmentPreviousExperiences()
            addFragmentToView(
                    getFragmentTransaction(activity),
                    fragment,
                    PREV_EXP_TAG_NAME,
                    bundle)
        }

        fun navigateToFragmentPreviousExperienceDetail(
                activity: FragmentActivity,
                uiPastExperience: UIPastExperience) {

            activity as AppCompatActivity

            val bundle = Bundle()
            bundle.putParcelable(PREV_EXP_EXTRA, uiPastExperience)

            val fragment = FragmentPreviousExpDetail()
            addFragmentToView(
                    getFragmentTransaction(activity),
                    fragment,
                    PREV_EXP_DET_TAG_NAME,
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