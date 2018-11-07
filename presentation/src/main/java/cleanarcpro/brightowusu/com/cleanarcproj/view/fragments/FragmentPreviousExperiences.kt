package cleanarcpro.brightowusu.com.cleanarcproj.view.fragments

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cleanarcpro.brightowusu.com.cleanarcproj.MainApplication
import cleanarcpro.brightowusu.com.cleanarcproj.R
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.UIPastExperience
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.UIPastExperiences
import cleanarcpro.brightowusu.com.cleanarcproj.di.components.DaggerFragmentPastExpComponent
import cleanarcpro.brightowusu.com.cleanarcproj.di.modules.FragmentPastExpModule
import cleanarcpro.brightowusu.com.cleanarcproj.listeners.OnItemClickListener
import cleanarcpro.brightowusu.com.cleanarcproj.utils.AppNavigationUtil
import cleanarcpro.brightowusu.com.cleanarcproj.view.adapters.PreviousExperiencesAdaptor
import cleanarcpro.brightowusu.com.cleanarcproj.viewmodels.DisplayPastExperiencesViewModel
import kotlinx.android.synthetic.main.fragment_past_exp.*
import kotlinx.android.synthetic.main.progress_layout.*
import javax.inject.Inject

class FragmentPreviousExperiences : BaseFragment() {

    @Inject
    lateinit var viewModel: DisplayPastExperiencesViewModel
    lateinit var adapter : PreviousExperiencesAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLiveDataObservers()
    }

    override fun initDependencies() {
        DaggerFragmentPastExpComponent.builder()
                .appComponent(MainApplication.getAppComponent())
                .fragmentPastExpModule(FragmentPastExpModule(this))
                .build()
                .inject(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_past_exp, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        initRecyclerView()
        loadUser()
    }

    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        // in content do not change the layout size of the RecyclerView
        previousExpRecyclerView.setHasFixedSize(true)
        previousExpRecyclerView.layoutManager = layoutManager

        adapter = PreviousExperiencesAdaptor()

        adapter.onItemClickListener = (object : OnItemClickListener<UIPastExperience>{
            override fun onItemClick(item: UIPastExperience) {
                AppNavigationUtil.navigateToFragmentPreviousExperienceDetail(activity!!, item)
            }
        })
        previousExpRecyclerView.adapter = adapter
    }

    private fun loadUser() {
        showLoadingState()
        viewModel.loadPastExperiences(1)
    }


    private fun initLiveDataObservers() {
        initGetUserObserver()
    }

    private fun initGetUserObserver() {
        // When there is a data change i.e our view model calls setValue()
        // this will result in onChanged being called in this observer
        viewModel.getLoadedPastExpLiveData().observe(
                this,
                Observer { pastExpList ->
                    displayPastExpList(pastExpList!!)
                })
    }

    private fun displayPastExpList(pastExperiencesWrapper : UIPastExperiences) {
        adapter.pastExperiences = pastExperiencesWrapper.pastExperiences
        hideLoadingState()
        showList()
    }

    private fun showList() {
        if (isRemoving)
            return
        previousExpRecyclerView.visibility = View.VISIBLE
    }

    private fun hideList() {
        if (isRemoving)
            return
        previousExpRecyclerView.visibility = View.GONE
    }

    private fun showLoadingState() {
        if (isRemoving)
            return
        progress_bar.visibility = View.VISIBLE
    }

    private fun hideLoadingState() {
        if (isRemoving)
            return
        progress_bar.visibility = View.GONE
    }
}