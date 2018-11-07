package cleanarcpro.brightowusu.com.cleanarcproj.view.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import cleanarcpro.brightowusu.com.cleanarcproj.R
import cleanarcpro.brightowusu.com.cleanarcproj.utils.AppNavigationUtil
import cleanarcpro.brightowusu.com.cleanarcproj.view.fragments.FragmentHome
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_tool_bar.*

class ActivityHome : AppCompatActivity() {

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                //message.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
               // message.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
               // message.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolBar()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        navigateToHomeFragment()
    }

    private fun navigateToHomeFragment() {
        //if (doesFragmentExist(FragmentHome::class.java) == null) {
            AppNavigationUtil.loadHomeFragment(this, R.id.fragment_container, 1 )
        //}
    }

    private fun doesFragmentExist(tagName : String): Fragment? {
        val fm = supportFragmentManager
        return fm.findFragmentByTag(tagName)
    }

    private fun initToolBar() {
        setSupportActionBar(customToolBar)
        supportActionBar!!.setHomeButtonEnabled(true)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

}
