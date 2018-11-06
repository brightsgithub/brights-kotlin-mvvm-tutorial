package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models

import com.google.gson.annotations.SerializedName

data class EntityPastExperiences(
        @SerializedName("past_experiences") val pastExperiences: List<EntityPastExperience>
)