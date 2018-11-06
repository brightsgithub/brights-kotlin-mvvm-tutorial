package cleanarcpro.brightowusu.com.cleanarcproj.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import cleanarcpro.brightowusu.com.cleanarcproj.R
import cleanarcpro.brightowusu.com.cleanarcproj.viewmodels.DisplayUserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class ActivityUser : AppCompatActivity() {

    //@Inject
    lateinit var displayUserViewModel: DisplayUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val displayUserPresenter = DisplayUserViewModel()
        displayUserPresenter.loadUser().subscribe({
            data.text = it.email + it.username
        })

    }

    fun initDependencies() {
//        DaggerActivityUserComponent.builder()
//                .coinMarketCapRepositoryComponent(MyApplication.get().getCoinMarketCapRepositoryComponent())
//                .portfolioFragmentModule(PortfolioFragmentModule(this))
//                .build()
//                .inject(this)
    }
}
