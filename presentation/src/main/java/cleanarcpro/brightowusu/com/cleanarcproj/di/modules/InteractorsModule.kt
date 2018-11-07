package cleanarcpro.brightowusu.com.cleanarcproj.di.modules;

import cleanarcpro.brightowusu.com.cleanarcproj.di.ActivityScope;
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository;
import cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors.*
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

    @Provides
    @ActivityScope
    fun providesGetPastExperiencesInteractor(userRepository: IUserRepository): IGetPastExperiencesInteractor {
        return GetPastExperiencesUseCase(userRepository)
    }

    @Provides
    @ActivityScope
    fun providesGetProSummaryInteractor(userRepository: IUserRepository): IGetProSummaryInteractor {
        return GetProSummaryUseCase(userRepository)
    }

    @Provides
    @ActivityScope
    fun providesGetTopicsOfKnowledgeInteractor(userRepository: IUserRepository): IGetTopicsOfKnowledgeInteractor {
        return GetTopicsOfKnowledgeUseCase(userRepository)
    }
}
