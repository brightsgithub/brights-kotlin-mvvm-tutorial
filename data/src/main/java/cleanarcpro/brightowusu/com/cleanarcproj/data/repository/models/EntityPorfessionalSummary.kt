package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models

import com.google.gson.annotations.SerializedName

data class EntityProfessionalSummary(
        @SerializedName("user_id") val userId: String,
        @SerializedName("professional_summary") val professionalSummary: String
)