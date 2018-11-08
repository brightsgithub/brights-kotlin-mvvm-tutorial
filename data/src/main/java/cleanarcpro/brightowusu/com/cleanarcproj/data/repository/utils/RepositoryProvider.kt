package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.utils

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.usercv.IUserCVApi
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
        fun providesUserRepository(retrofit: Retrofit): IUserRepository {
            return UserRepositoryImpl(retrofit.create(IUserCVApi::class.java))
        }
    }


}