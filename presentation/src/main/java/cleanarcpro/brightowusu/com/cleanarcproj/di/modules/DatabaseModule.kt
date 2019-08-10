package cleanarcpro.brightowusu.com.cleanarcproj.di.modules

import android.content.Context
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.db.dao.PastExperienceDao
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.db.dao.ProSummaryDao
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.db.dao.UserDao
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.utils.DatabaseProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Bright Owusu-Amankwaa on 2019-08-19.
 */
@Module(includes = [AppModule::class])
class DatabaseModule {

    @Singleton
    @Provides
    fun providesUserDao(appContext: Context) : UserDao {
        return DatabaseProvider.providesUserDao(appContext)
    }

    @Singleton
    @Provides
    fun providesProSummaryDao(appContext: Context) : ProSummaryDao {
        return DatabaseProvider.providesProSummaryDao(appContext)
    }

    @Singleton
    @Provides
    fun providesPastExperienceDao(appContext: Context) : PastExperienceDao {
        return DatabaseProvider.providesPastExperienceDao(appContext)
    }

}