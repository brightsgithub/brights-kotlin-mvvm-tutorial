package cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.DomainTopicsOfKnowladge

interface IGetTopicsOfKnowledgeInteractor : UseCase<DomainTopicsOfKnowladge>{

    fun setUserId(userId: Int)
}