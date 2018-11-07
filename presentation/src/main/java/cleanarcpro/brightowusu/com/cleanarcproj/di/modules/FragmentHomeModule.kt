package cleanarcpro.brightowusu.com.cleanarcproj.di.modules

import android.arch.lifecycle.ViewModelProviders
import cleanarcpro.brightowusu.com.cleanarcproj.di.ActivityScope
import cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors.IGetUserInteractor
import cleanarcpro.brightowusu.com.cleanarcproj.view.fragments.FragmentHome
import cleanarcpro.brightowusu.com.cleanarcproj.viewmodels.DisplayUserViewModel
import dagger.Module
import dagger.Provides

@Module
class FragmentHomeModule(val fragmentHome: FragmentHome) {

    @Provides
    @ActivityScope
    fun providesDisplayUserViewModel(getUserInteractor: IGetUserInteractor): DisplayUserViewModel {

        val displayUserViewModel
                = ViewModelProviders.of(fragmentHome).get(DisplayUserViewModel::class.java)

        displayUserViewModel.userInteractor = getUserInteractor
        return displayUserViewModel

    }

}