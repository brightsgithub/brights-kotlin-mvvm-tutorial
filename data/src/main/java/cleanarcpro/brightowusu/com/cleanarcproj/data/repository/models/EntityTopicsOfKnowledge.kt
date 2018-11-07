package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models

import com.google.gson.annotations.SerializedName

data class EntityTopicsOfKnowledge(
        @SerializedName("topics_of_knowladge") val topicsOfKnowladge: List<EntityTopic>
)