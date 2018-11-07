package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models

import com.google.gson.annotations.SerializedName

data class EntityUser(
        @SerializedName("user_id") val userId: Int,
        @SerializedName("name") val userName: String,
        @SerializedName("email") val email: String,
        @SerializedName("phone") val phone: String
)