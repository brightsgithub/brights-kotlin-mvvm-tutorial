package cleanarcpro.brightowusu.com.cleanarcproj.domain.interactors

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import org.jetbrains.annotations.NotNull

/**
 * Created by Bright Owusu-Amankwaa on 2019-08-10.
 */
abstract class BaseUseCase<out Type, in Params> where Type : Any {

    abstract suspend fun execute(@NotNull scope: CoroutineScope, params: Params): Pair<Exception?, Type?>

    open operator fun invoke(
            scope: CoroutineScope,
            params: Params,
            onResult: (Pair<Exception?, Type?>) -> Unit = {}
    ) {
        val backgroundJob = scope.async { execute(scope, params) }
        scope.launch { onResult(backgroundJob.await()) }
    }

}