package cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.DomainTopicsOfKnowladge
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import io.reactivex.Observable

class GetTopicsOfKnowladgeUseCase(val userRepository: IUserRepository) : IGetTopicsOfKnowladgeInteractor{

    var userId: Int? = null
    override fun setUserId(userId: Int) {
        this.userId = userId
    }

    override fun execute(): Observable<DomainTopicsOfKnowladge> {

        if(userId == null) {
            throw IllegalStateException("User id cannot be null")
        }

        return userRepository.getTopicsOfKnowladge(userId!!)
    }

}