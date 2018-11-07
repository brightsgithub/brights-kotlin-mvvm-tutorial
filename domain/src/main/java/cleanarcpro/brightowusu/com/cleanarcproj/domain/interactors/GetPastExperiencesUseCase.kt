package cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.DomainPastExperiences
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import io.reactivex.Observable

class GetPastExperiencesUseCase(val userRepository: IUserRepository) : IGetPastExperiencesInteractor {

    var userId: Int? = null
    override fun setUserId(userId: Int) {
        this.userId = userId
    }

    override fun execute(): Observable<DomainPastExperiences> {

        if(userId == null) {
            throw IllegalStateException("User id cannot be null")
        }

        return userRepository.getPastExperiences(userId!!)
    }

}