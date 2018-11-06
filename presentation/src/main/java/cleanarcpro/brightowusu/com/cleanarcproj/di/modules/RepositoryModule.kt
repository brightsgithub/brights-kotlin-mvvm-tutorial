package cleanarcpro.brightowusu.com.cleanarcproj.di.modules;

import javax.inject.Singleton;

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.UserRepositoryImpl;
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository;
import dagger.Module;
import dagger.Provides;

/**
 *
 */
@Module(includes = [AppModule::class])
class RepositoryModule {

    @Singleton
    @Provides
    fun providesUserRepository(): IUserRepository {
        return UserRepositoryImpl()
    }

}
