package cleanarcpro.brightowusu.com.cleanarcproj.data.repository.mappers

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.DomainTopicsOfKnowledge
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.UITopic
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.UITopicsOfKnowledge
import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.EntityTopicsOfKnowledge

class MapDomainTopicsToUI {


    companion object {

        fun transform(domainTopicsOfKnowledge: DomainTopicsOfKnowledge): UITopicsOfKnowledge {

            val topics = ArrayList<UITopic>()
            domainTopicsOfKnowledge.topicsOfKnowladge.forEach {

                val topic = UITopic(it.id, it.topic, it.topicDetail)
                topics.add(topic)
            }
            return UITopicsOfKnowledge(topics)
        }


    }
}