package cleanarcpro.brightowusu.com.cleanarcproj.di.modules;

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.db.dao.PastExperienceDao
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.db.dao.ProSummaryDao
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.db.dao.UserDao
import javax.inject.Singleton

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.utils.RepositoryProvider
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit

/**
 * Repository Implementations.
 *
 * Created by Bright Owusu-Amankwaa
 */
@Module(includes = [AppModule::class])
class RepositoryModule {

    @Singleton
    @Provides
    fun providesUserRepository(
            retrofit: Retrofit,
            userDao: UserDao,
            pastExperienceDao: PastExperienceDao,
            proSummaryDao: ProSummaryDao
            ): IUserRepository {
        return RepositoryProvider.providesUserRepository(
                retrofit,
                userDao,
                proSummaryDao,
                pastExperienceDao)
    }

}
