package cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors

import rx.Observable

interface UseCase<T> {

    fun execute(): T
}