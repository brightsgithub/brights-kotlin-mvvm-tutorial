package cleanarcpro.brightowusu.com.cleanarcproj.di.modules

import androidx.lifecycle.ViewModelProviders
import cleanarcpro.brightowusu.com.cleanarcproj.di.ActivityScope
import cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors.IGetAboutUserInteractor
import cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors.IGetUserInteractor
import cleanarcpro.brightowusu.com.cleanarcproj.view.fragments.FragmentHome
import cleanarcpro.brightowusu.com.cleanarcproj.viewmodels.DisplayUserViewModel
import dagger.Module
import dagger.Provides

/**
 * Provides what we need to the specific Fragment.
 *
 * Created by Bright Owusu-Amankwaa
 */
@Module
class FragmentHomeModule(val fragmentHome: FragmentHome) {

    @Provides
    @ActivityScope
    fun providesDisplayUserViewModel(
            aboutUserInteractor: IGetAboutUserInteractor): DisplayUserViewModel {

        val displayUserViewModel
                = ViewModelProviders.of(fragmentHome).get(DisplayUserViewModel::class.java)

        displayUserViewModel.aboutUserInteractor = aboutUserInteractor
        return displayUserViewModel

    }

}