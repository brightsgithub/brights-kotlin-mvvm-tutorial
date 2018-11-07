package cleanarcpro.brightowusu.com.cleanarcproj.view

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import cleanarcpro.brightowusu.com.cleanarcproj.MainApplication
import cleanarcpro.brightowusu.com.cleanarcproj.R
import cleanarcpro.brightowusu.com.cleanarcproj.di.components.DaggerActivityUserComponent
import cleanarcpro.brightowusu.com.cleanarcproj.di.modules.ActivityUserModule
import cleanarcpro.brightowusu.com.cleanarcproj.models.UIUser
import cleanarcpro.brightowusu.com.cleanarcproj.viewmodels.DisplayUserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class ActivityUser : AppCompatActivity() {

    @Inject
    lateinit var displayUserViewModel: DisplayUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initDependencies()
        initLiveDataObservers()
        loadUser()
    }

    private fun loadUser() {
        displayUserViewModel.loadUser(1)
    }

    fun initDependencies() {
        DaggerActivityUserComponent.builder()
                .appComponent(MainApplication.getAppComponent())
                .activityUserModule(ActivityUserModule(this))
                .build()
                .inject(this)
    }


    private fun initLiveDataObservers() {
        initGetUserObserver()
    }

    private fun initGetUserObserver() {
        // When there is a data change i.e our view model calls setValue()
        // this will result in onChanged being called in this observer
        displayUserViewModel.getLoadedUserLiveData().observe(
                this,
                Observer { user ->
                    updateUserInfo(user!!)
                })
    }

    private fun updateUserInfo(user :UIUser) {
        data.text = user.email + user.name
    }
}
