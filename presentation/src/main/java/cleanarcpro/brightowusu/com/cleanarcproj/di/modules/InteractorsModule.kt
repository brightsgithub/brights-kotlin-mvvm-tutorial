package cleanarcpro.brightowusu.com.cleanarcproj.di.modules;

import cleanarcpro.brightowusu.com.cleanarcproj.di.ActivityScope
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors.*
import dagger.Module
import dagger.Provides

/**
 * UseCase Implementations
 *
 * Created by Bright Owusu-Amankwaa
 */
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
    fun providesGetAboutUserInteractor(
            userInteractor: IGetUserInteractor,
            summaryInteractor: IGetProSummaryInteractor): IGetAboutUserInteractor {
        return GetAboutUserUseCase(summaryInteractor, userInteractor)
    }

}
