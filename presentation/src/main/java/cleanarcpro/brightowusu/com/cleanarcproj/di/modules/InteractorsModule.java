package cleanarcpro.brightowusu.com.cleanarcproj.di.modules;

import javax.inject.Singleton;

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.UserRepositoryImpl;
import cleanarcpro.brightowusu.com.cleanarcproj.di.ActivityScope;
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository;
import cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors.GetUserUseCase;
import cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors.IGetUserInteractor;
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainUser;
import dagger.Module;
import dagger.Provides;
import rx.Observable;
//@Module
public class InteractorsModule {

    //@Provides
//    @ActivityScope
//    public IGetUserInteractor providesGetUserCurrencyInteractor() {
//        return new GetUserUseCase(new UserRepositoryImpl());
//    }
}
