package cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors

import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import cleanarcpro.brightowusu.com.cleanarcproj.domain.models.DomainUser
import io.reactivex.Observable


class GetUserUseCase (val userRepository: IUserRepository) : IGetUserInteractor {

    override fun execute(): Observable<DomainUser> {
        return userRepository.getUser()
    }


}