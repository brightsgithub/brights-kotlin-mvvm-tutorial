package cleanarcpro.brightowusu.com.cleanarcproj.di.modules;

import javax.inject.Singleton

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.utils.RepositoryProvider
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit

/**
 *
 */
@Module(includes = [AppModule::class])
class RepositoryModule {

    @Singleton
    @Provides
    fun providesUserRepository(retrofit: Retrofit): IUserRepository {
        return RepositoryProvider.providesUserRepository(retrofit)
    }

}
