package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.mappers

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.DomainTopic
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.DomainTopicsOfKnowledge
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.EntityTopicsOfKnowledge

class MapEntityTopicsToDomain {


    companion object {

        fun transform(entityTopicsOfKnowledge: EntityTopicsOfKnowledge): DomainTopicsOfKnowledge {

            val topics = ArrayList<DomainTopic>()
            entityTopicsOfKnowledge.topicsOfKnowladge.forEach {

                val topic = DomainTopic(it.id, it.topic, it.topicDetail)
                topics.add(topic)
            }
            return DomainTopicsOfKnowledge(topics)
        }


    }
}