package cleanarcpro.brightowusu.com.cleanarcproj.di.modules

import androidx.lifecycle.ViewModelProviders
import cleanarcpro.brightowusu.com.cleanarcproj.di.ActivityScope
import cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors.IGetPastExperiencesInteractor
import cleanarcpro.brightowusu.com.cleanarcproj.view.fragments.FragmentPreviousExperiences
import cleanarcpro.brightowusu.com.cleanarcproj.viewmodels.DisplayPastExperiencesViewModel
import dagger.Module
import dagger.Provides
import javax.inject.Named

/**
 * Provides what we need to the specific Fragment.
 *
 * Created by Bright Owusu-Amankwaa
 */
@Module
class FragmentPastExpModule(val fragmentPreviousExperiences: FragmentPreviousExperiences) {

    @Provides
    @ActivityScope
    fun providesDisplayPastExperiencesViewModel(pastExperiencesInteractor: IGetPastExperiencesInteractor):
            DisplayPastExperiencesViewModel {
        val vm= ViewModelProviders.of(fragmentPreviousExperiences).get(DisplayPastExperiencesViewModel::class.java)
        vm.pastExperienceInteractor = pastExperiencesInteractor
        return vm

    }

}