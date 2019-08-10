package cleanarcpro.brightowusu.com.cleanarcproj.view.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import cleanarcpro.brightowusu.com.cleanarcproj.R
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.UIPastExperience
import cleanarcpro.brightowusu.com.cleanarcproj.utils.AppNavigationUtil
import cleanarcpro.brightowusu.com.cleanarcproj.view.fragments.BaseFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_home_new.*
import kotlinx.android.synthetic.main.custom_tool_bar.*

/**
 * Host Activity.
 *
 * Similar to the new Android Navigation Component:
 * https://developer.android.com/topic/libraries/architecture/navigation/navigation-implementing
 * (Only available in Android Studio 3.2 Canary 14 or higher)
 *
 * We have one activity that will swap fragments in and out.
 *
 * Created by Bright Owusu-Amankwaa
 */
class ActivityHome : AppCompatActivity() , BaseFragment.BaseFragmentCallBack {

    private val userId = 1L
    private var snackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_new)
        initToolBar()
        initFabListener()
        navigateToHomeFragment()
    }

    private fun initFabListener() {

        fab.setOnClickListener { view ->
            AppNavigationUtil.navigateToFragmentPreviousExperiences(this, userId)
        }
    }

    private fun navigateToHomeFragment() {
        AppNavigationUtil.navigateToHomeFragment(this, userId )
    }

    private fun initToolBar() {
        setSupportActionBar(customToolBar)
    }

    override fun showFab() {
        fab.show()
    }

    override fun hideFab() {
        fab.hide()
    }

    override fun showUpButton() {
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun hideUpButton() {
        supportActionBar!!.setHomeButtonEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
    }

    override fun setToolBarTitle(title: String) {
        supportActionBar!!.title = title
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if(item!!.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun navigateToFragmentPreviousExperienceDetail(item: UIPastExperience) {
        AppNavigationUtil.navigateToFragmentPreviousExperienceDetail(this, item)
    }

    override fun onBackPressed() {
        val f = supportFragmentManager.findFragmentById(R.id.fragment_container)
        f as BaseFragment
        if(f.onBackPressedShouldWeCloseActivity()) {
            super.onBackPressed()
            finish()
        } else {
            super.onBackPressed()
        }

    }

    override fun showSnackbar(msg: String, length: Int, bgHexColor: String?, textHexColor: String?) {
        snackbar = Snackbar.make(window.decorView.findViewById(R.id.parent_container), msg, length)
        bgHexColor?.let {
            snackbar?.view?.setBackgroundColor(Color.parseColor(it))
        }

        textHexColor?.let {
            val textView = snackbar?.view?.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
            textView.setTextColor(Color.parseColor(it))
        }


        snackbar?.show()
    }
}
