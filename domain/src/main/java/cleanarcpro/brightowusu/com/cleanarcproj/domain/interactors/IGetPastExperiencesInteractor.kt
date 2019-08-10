package cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.models.DomainPastExperiences
import java.lang.Exception

interface IGetPastExperiencesInteractor : UseCase<DomainPastExperiences?, Exception?>{

    fun setUserId(userId: Long)
}