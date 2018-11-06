package cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors

import io.reactivex.Observable

interface UseCase<T> {

    fun execute(): Observable<T>
}