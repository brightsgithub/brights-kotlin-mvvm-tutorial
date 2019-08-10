package cleanarcpro.brightowusu.com.cleanarcproj.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import cleanarcpro.brightowusu.com.cleanarcproj.MainApplication
import cleanarcpro.brightowusu.com.cleanarcproj.R
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.UIPastExperience
import cleanarcpro.brightowusu.com.cleanarcproj.utils.Constants.Companion.PREV_EXP_EXTRA
import kotlinx.android.synthetic.main.fragment_past_exp_det.*

/**
 * Displays the Previous experience in detail.
 *
 * Created by Bright Owusu-Amankwaa
 */
class FragmentPreviousExpDetail : BaseFragment() {

    private lateinit var uiPastExperience: UIPastExperience

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        uiPastExperience = arguments!!.getParcelable(PREV_EXP_EXTRA)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_past_exp_det, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    /**
     * Init View
     */
    private fun initView() {
        modifyContainer()
        displayPreviousExperienceDetail()
    }

    /**
     * Changes title, fab, up button etc
     */
    private fun modifyContainer() {
        baseFragmentCallBack.hideFab()
        baseFragmentCallBack.showUpButton()
        baseFragmentCallBack.setToolBarTitle(uiPastExperience.companyName)
    }

    /**
     * Displays the previous experience detail
     */
    private fun displayPreviousExperienceDetail() {
        companyName.text = uiPastExperience.companyName
        roleName.text = uiPastExperience.roleName
        MainApplication.getPicasso().load(uiPastExperience.companyLogo).into(companyLogo)
        startDate.text = uiPastExperience.datesStart
        endDate.text = uiPastExperience.dateEnd
        responsibilities.text = uiPastExperience.responsibilities
        techUsed.text = uiPastExperience.techUsed
    }


    override fun initDependencies() {
        // none
    }

    override fun onBackPressedShouldWeCloseActivity(): Boolean {
        return false
    }

    override fun handleDeviceHasConnection() {

    }

    override fun handleDeviceHasNoConnection() {
    }

}