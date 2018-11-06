package cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors

import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainUser
import rx.Observable

class GetUserUseCase(val userRepository: IUserRepository) : IGetUserInteractor<Observable<DomainUser>> {
    override fun execute(): Observable<DomainUser> {
        return userRepository.getUser()
    }
}