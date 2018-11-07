package cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors

import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainProfessionalSummary
import io.reactivex.Observable

class GetProSummaryUseCase(val userRepository: IUserRepository) : IGetProSummaryInteractor {

    var userId: Int? = null
    override fun setUserId(userId: Int) {
        this.userId = userId
    }

    override fun execute(): Observable<DomainProfessionalSummary> {

        if(userId == null) {
            throw IllegalStateException("User id cannot be null")
        }

        return userRepository.getProfessionalSummary(userId!!)
    }

}