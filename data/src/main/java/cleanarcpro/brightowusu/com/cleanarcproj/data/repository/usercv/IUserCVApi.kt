package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.usercv

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.*
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface IUserCVApi {

    @GET("/brightsgithub/random-data/master/fake_data/users/{userId}/user_details.json")
    fun getUserDetails(@Path("userId") userId: Int): Observable<EntityUser>

    @GET("/brightsgithub/random-data/master/fake_data/users/{userId}/past_experiences.json")
    fun getPastExperiences(@Path("userId") userId: Int): Observable<EntityPastExperiences>

    @GET("/brightsgithub/random-data/master/fake_data/users/{userId}/professional_summary.json")
    fun getProfessionalSummary(@Path("userId") userId: Int): Observable<EntityProfessionalSummary>

    @GET("/brightsgithub/random-data/master/fake_data/users/{userId}/topics_of_knowladge.json")
    fun getTopicsOfKnowladge(@Path("userId") userId: Int): Observable<EntityTopicsOfKnowledge>
}
