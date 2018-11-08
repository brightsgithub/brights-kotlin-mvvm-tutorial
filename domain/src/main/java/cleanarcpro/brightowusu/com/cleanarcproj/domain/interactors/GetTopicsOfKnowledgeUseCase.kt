package cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.DomainTopicsOfKnowledge
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import io.reactivex.Observable

/**
 * Get the users topics of knowladge and perform any business logic here if needed.
 *
 * Created by Bright Owusu-Amankwaa
 */
class GetTopicsOfKnowledgeUseCase(val userRepository: IUserRepository) : IGetTopicsOfKnowledgeInteractor{

    var userId: Int? = null
    override fun setUserId(userId: Int) {
        this.userId = userId
    }

    override fun execute(): Observable<DomainTopicsOfKnowledge> {

        if(userId == null) {
            throw IllegalStateException("User id cannot be null")
        }

        return userRepository.getTopicsOfKnowladge(userId!!)
    }

}