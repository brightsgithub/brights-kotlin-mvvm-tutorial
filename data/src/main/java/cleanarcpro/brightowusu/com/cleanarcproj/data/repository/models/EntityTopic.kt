package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models

import com.google.gson.annotations.SerializedName

data class EntityTopic(
        @SerializedName("id") val id: String,
        @SerializedName("topic") val topic: String,
        @SerializedName("topic_detail") val topicDetail: String
)