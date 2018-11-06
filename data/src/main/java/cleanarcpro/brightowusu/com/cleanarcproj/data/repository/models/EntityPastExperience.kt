package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models

import com.google.gson.annotations.SerializedName

data class EntityPastExperience(
        @SerializedName("id") val id: String,
        @SerializedName("company_name") val companyName: String,
        @SerializedName("role_name") val roleName: String,
        @SerializedName("dates_start") val datesStart: String,
        @SerializedName("date_end") val dateEnd: String,
        @SerializedName("responsibilities") val responsibilities: String,
        @SerializedName("company_logo") val companyLogo: String
)