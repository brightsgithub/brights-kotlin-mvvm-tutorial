package cleanarcpro.brightowusu.com.cleanarcproj.view.fragments

import android.arch.lifecycle.Observer
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
import javax.inject.Inject

class FragmentHome : BaseFragment() {

    @Inject
    lateinit var displayUserViewModel: DisplayUserViewModel

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

    private fun initView() {
        loadUser()
    }



    private fun loadUser() {
        displayUserViewModel.loadAboutUserInfo(1)
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

    private fun updateUserInfo(uiAboutUser : UIAboutUser) {
        val user = uiAboutUser.uiUser
        userName.text = user.name
        email.text = user.email
        phone.text = user.phone

        professionalSummary.text = uiAboutUser.uiSummary.professionalSummary
    }

}