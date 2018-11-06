package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.mappers

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.DomainTopic
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.DomainTopicsOfKnowladge
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.EntityTopicsOfKnowladge

class MapEntityTopicsToDomain {


    companion object {

        fun transform(entityTopicsOfKnowladge: EntityTopicsOfKnowladge): DomainTopicsOfKnowladge {

            val topics = ArrayList<DomainTopic>()
            entityTopicsOfKnowladge.topicsOfKnowladge.forEach {

                val topic = DomainTopic(it.id, it.topic, it.topicDetail)
                topics.add(topic)
            }

            val domainTopicsOfKnowladge = DomainTopicsOfKnowladge(topics)

            return domainTopicsOfKnowladge
        }


    }
}