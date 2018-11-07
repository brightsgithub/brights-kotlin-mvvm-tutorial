package cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.DomainPastExperiences

interface IGetPastExperiencesInteractor : UseCase<DomainPastExperiences>{

    fun setUserId(userId: Int)
}