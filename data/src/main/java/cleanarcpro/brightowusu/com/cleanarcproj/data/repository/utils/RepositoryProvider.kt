package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.utils

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.db.dao.PastExperienceDao
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.db.dao.ProSummaryDao
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.db.dao.UserDao
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.network.IUserCVApi
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.usercv.UserRepositoryImpl
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import retrofit2.Retrofit

/**
 * For testing purposes, this is defined in a single place, which will be used by Dagger and mocked
 * tests. This serves as a single place for the setup.
 * This is a work around since injecting dependencies into androidTest class is difficult.
 *
 * Created by Bright Owusu-Amankwaa
 */
class RepositoryProvider {

    companion object {
        fun providesUserRepository(
                retrofit: Retrofit,
                userDao: UserDao,
                proSummaryDao: ProSummaryDao,
                pastExperienceDao: PastExperienceDao
                ): IUserRepository {

            return UserRepositoryImpl(
                    retrofit.create(IUserCVApi::class.java),
                    userDao,
                    proSummaryDao,
                    pastExperienceDao)
        }
    }
}