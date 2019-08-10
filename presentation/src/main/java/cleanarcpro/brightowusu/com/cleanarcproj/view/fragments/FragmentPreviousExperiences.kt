package cleanarcpro.brightowusu.com.cleanarcproj.view.fragments

import androidx.lifecycle.Observer
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
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
import cleanarcpro.brightowusu.com.cleanarcproj.view.adapters.PreviousExperiencesAdaptor
import cleanarcpro.brightowusu.com.cleanarcproj.viewmodels.DisplayPastExperiencesViewModel
import cleanarcpro.brightowusu.com.cleanarcproj.viewmodels.states.PastExperiencesViewState
import kotlinx.android.synthetic.main.fragment_past_exp.*
import kotlinx.android.synthetic.main.progress_layout.*
import javax.inject.Inject

/**
 * Created by Bright Owusu-Amankwaa
 */
class FragmentPreviousExperiences : BaseFragment() {

    @Inject
    lateinit var viewModel: DisplayPastExperiencesViewModel
    lateinit var adapter : PreviousExperiencesAdaptor
    var doesPastExpExist: Boolean? = null

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

    /**
     * Init the view.
     */
    private fun initView() {
        modifyContainer()
        initRecyclerView()
        loadPastExperiences()
    }

    /**
     * Changes title, fab, up button etc.
     */
    private fun modifyContainer() {
        baseFragmentCallBack.hideFab()
        baseFragmentCallBack.showUpButton()
        baseFragmentCallBack.setToolBarTitle(getString(R.string.prev_exp))
    }

    /**
     * Init the RecyclerView used for displaying all past experiences.
     */
    private fun initRecyclerView() {
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        // in content do not change the layout size of the RecyclerView
        previousExpRecyclerView.setHasFixedSize(true)
        previousExpRecyclerView.layoutManager = layoutManager

        adapter = PreviousExperiencesAdaptor()

        adapter.onItemClickListener = (object : OnItemClickListener<UIPastExperience>{
            override fun onItemClick(item: UIPastExperience) {
                baseFragmentCallBack.navigateToFragmentPreviousExperienceDetail(item)
            }
        })
        previousExpRecyclerView.adapter = adapter
    }

    /**
     * Load the users past experiences.
     */
    private fun loadPastExperiences() {
        showLoadingState(progress_bar)
        viewModel.loadPastExperiences(1)
    }

    /**
     * Init any observers
     */
    private fun initLiveDataObservers() {
        initPastExperienceObserver()
    }

    /**
     * Listen in for any updates for UIPastExperience
     */
    private fun initPastExperienceObserver() {
        // When there is a data change i.e our view model calls setValue()
        // this will result in onChanged being called in this observer
        viewModel.getDisplayPastExperiencesViewState().observe(
                this,
                Observer { state ->

                    when(state) {
                        is PastExperiencesViewState.Success -> {
                            displayPastExpList(state.pastExperiences)
                        }
                        is PastExperiencesViewState.PastExperiencesExists -> {doesPastExpExist = true}
                        is PastExperiencesViewState.PastExperiencesDoesNotExist-> {doesPastExpExist = false}
                    }
                })
    }

    /**
     * Displays past experiences.
     */
    private fun displayPastExpList(pastExperiencesWrapper : UIPastExperiences) {
        adapter.pastExperiences = pastExperiencesWrapper.pastExperiences
        hideLoadingState(progress_bar)
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

    override fun onBackPressedShouldWeCloseActivity() :Boolean {
        return false
    }

    override fun handleDeviceHasConnection() {
        if(doesPastExpExist != null && !doesPastExpExist!!) {
            loadPastExperiences()
        }
    }

    override fun handleDeviceHasNoConnection() {
    }
}