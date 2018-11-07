package cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.DomainTopicsOfKnowledge

interface IGetTopicsOfKnowledgeInteractor : UseCase<DomainTopicsOfKnowledge>{

    fun setUserId(userId: Int)
}