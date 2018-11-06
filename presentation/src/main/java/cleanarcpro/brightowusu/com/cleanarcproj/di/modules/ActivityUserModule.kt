package cleanarcpro.brightowusu.com.cleanarcproj.di.modules;

import android.arch.lifecycle.ViewModelProviders;

import cleanarcpro.brightowusu.com.cleanarcproj.di.ActivityScope;
import cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors.IGetUserInteractor;
import cleanarcpro.brightowusu.com.cleanarcproj.view.ActivityUser;
import cleanarcpro.brightowusu.com.cleanarcproj.viewmodels.DisplayUserViewModel;
import dagger.Module;
import dagger.Provides;

@Module
class ActivityUserModule(val activityUser: ActivityUser) {

    @Provides
    @ActivityScope
    fun providesDisplayUserViewModel(getUserInteractor: IGetUserInteractor ): DisplayUserViewModel{

        val displayUserViewModel
                = ViewModelProviders.of(activityUser).get(DisplayUserViewModel::class.java)

        displayUserViewModel.userInteractor = getUserInteractor
        return displayUserViewModel

    }
}
