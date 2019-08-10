package cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors

import kotlinx.coroutines.CoroutineScope

/**
 * UseCase
 * Created by Bright Owusu-Amankwaa
 */
interface UseCase<T,Error> {

    suspend fun execute(scope: CoroutineScope) : Pair<T?, Error?>
}