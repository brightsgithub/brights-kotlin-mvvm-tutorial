package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.usercv

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.EntityUser
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface IUserCVApi {

    @GET("/random-data/master/fake_data/users/{userId}/user_details.json")
    fun getUserDetails(@Path("userId") userId: String): Observable<EntityUser>

}