package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.utils

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.usercv.IUserCVApi
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.usercv.UserRepositoryImpl
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import retrofit2.Retrofit

class RepositoryProvider {

    companion object {
        fun providesUserRepository(retrofit: Retrofit): IUserRepository {
            return UserRepositoryImpl(retrofit.create(IUserCVApi::class.java))
        }
    }


}