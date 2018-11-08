package cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors

import io.reactivex.Observable

/**
 * UseCase
 * Created by Bright Owusu-Amankwaa
 */
interface UseCase<T> {
    // Strategy Pattern - try to be as granular as possible. Just execute().
    fun execute(): Observable<T>
}