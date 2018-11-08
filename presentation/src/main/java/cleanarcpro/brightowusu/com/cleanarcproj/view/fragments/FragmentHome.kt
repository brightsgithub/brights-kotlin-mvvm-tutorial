package cleanarcpro.brightowusu.com.cleanarcproj.view.fragments

import android.arch.lifecycle.Observer
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cleanarcpro.brightowusu.com.cleanarcproj.MainApplication
import cleanarcpro.brightowusu.com.cleanarcproj.R
import cleanarcpro.brightowusu.com.cleanarcproj.di.components.DaggerFragmentHomeComponent
import cleanarcpro.brightowusu.com.cleanarcproj.di.modules.FragmentHomeModule
import cleanarcpro.brightowusu.com.cleanarcproj.models.UIAboutUser
import cleanarcpro.brightowusu.com.cleanarcproj.viewmodels.DisplayUserViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.progress_layout.*
import javax.inject.Inject

/**
 * Displays the home screen.
 *
 * Created by Bright Owusu-Amankwaa
 */
class FragmentHome : BaseFragment() {

    @Inject
    lateinit var displayUserViewModel: DisplayUserViewModel

    private lateinit var homeFragmentCallBack : HomeFragmentCallBack
    private interface HomeFragmentCallBack {
        fun closeApp()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLiveDataObservers()
    }

    override fun initDependencies() {
        DaggerFragmentHomeComponent.builder()
                .appComponent(MainApplication.getAppComponent())
                .fragmentHomeModule(FragmentHomeModule(this))
                .build()
                .inject(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    /**
     * Performs view setup.
     */
    private fun initView() {
        modifyContainer()
        showLoadingState(progress_bar)
        loadUser()
    }

    /**
     * Changes title, fab, up button etc
     */
    private fun modifyContainer() {
        baseFragmentCallBack.showFab()
        baseFragmentCallBack.hideUpButton()
    }

    override fun showLoadingState(view: View) {
        home_container.visibility = View.GONE
        super.showLoadingState(view)
    }

    override fun hideLoadingState(view: View) {
        home_container.visibility = View.VISIBLE
        super.hideLoadingState(view)
    }

    /**
     * Loads user
     */
    private fun loadUser() {
        displayUserViewModel.loadAboutUserInfo(1)
    }

    /**
     * Initialize Observers
     */
    private fun initLiveDataObservers() {
        initGetUserObserver()
    }

    /**
     * Listen in for any User that has been received.
     */
    private fun initGetUserObserver() {
        // When there is a data change i.e our view model calls setValue()
        // this will result in onChanged being called in this observer
        displayUserViewModel.getLoadedUserLiveData().observe(
                this,
                Observer { user ->
                    updateUserInfo(user!!)
                    hideLoadingState(progress_bar)
                })
    }

    /**
     * Displays user information.
     */
    private fun updateUserInfo(uiAboutUser : UIAboutUser) {
        val user = uiAboutUser.uiUser
        userName.text = user.name
        email.text = user.email
        phone.text = user.phone

        professionalSummary.text = uiAboutUser.uiSummary.professionalSummary
    }

    override fun onBackPressedShouldWeCloseActivity(): Boolean {
        return true
    }
}