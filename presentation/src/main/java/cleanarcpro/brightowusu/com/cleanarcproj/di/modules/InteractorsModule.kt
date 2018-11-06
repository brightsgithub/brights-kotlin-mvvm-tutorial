package cleanarcpro.brightowusu.com.cleanarcproj.di.modules;

import cleanarcpro.brightowusu.com.cleanarcproj.di.ActivityScope;
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository;
import cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors.GetUserUseCase;
import cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors.IGetUserInteractor;
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainUser
import dagger.Module;
import dagger.Provides;
@Module
class InteractorsModule {

    @Provides
    @ActivityScope
    fun providesGetUserCurrencyInteractor(userRepository: IUserRepository): IGetUserInteractor {
        return GetUserUseCase(userRepository)
    }
}
