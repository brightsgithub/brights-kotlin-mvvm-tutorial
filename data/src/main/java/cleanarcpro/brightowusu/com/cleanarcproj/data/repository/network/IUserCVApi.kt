package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.network

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.*
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface IUserCVApi {

    @GET("/brightsgithub/random-data/master/fake_data/users/{userId}/user_details.json")
    suspend fun getUserDetails(@Path("userId") userId: Long): EntityUser

    @GET("/brightsgithub/random-data/master/fake_data/users/{userId}/past_experiences.json")
    suspend fun getPastExperiences(@Path("userId") userId: Long): EntityPastExperiences

    @GET("/brightsgithub/random-data/master/fake_data/users/{userId}/professional_summary.json")
    suspend fun getProfessionalSummary(@Path("userId") userId: Long): EntityProfessionalSummary
}
