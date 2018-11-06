package cleanarcpro.brightowusu.com.cleanarcproj.di.modules;

import android.arch.lifecycle.ViewModelProviders;

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.UserRepositoryImpl;
import cleanarcpro.brightowusu.com.cleanarcproj.di.ActivityScope;
import cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors.GetUserUseCase;
import cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors.IGetUserInteractor;
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainUser;
import cleanarcpro.brightowusu.com.cleanarcproj.view.ActivityUser;
import cleanarcpro.brightowusu.com.cleanarcproj.viewmodels.DisplayUserViewModel;
import dagger.Module;
import dagger.Provides;
import rx.Observable;

//@Module
public class ActivityUserModule {

    private final ActivityUser activityUser;

    public ActivityUserModule(ActivityUser activityUser) {
        this.activityUser = activityUser;
    }

    //@Provides
    //@ActivityScope
    public DisplayUserViewModel providesDisplayUserViewModel(final IGetUserInteractor getUserInteractor) {

        DisplayUserViewModel displayUserViewModel
                = ViewModelProviders.of(activityUser).get(DisplayUserViewModel.class);

        displayUserViewModel.setUserInteractor(getUserInteractor);
        return displayUserViewModel;
    }
}
