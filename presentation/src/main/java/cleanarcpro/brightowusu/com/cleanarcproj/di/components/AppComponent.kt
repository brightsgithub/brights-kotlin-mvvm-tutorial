package cleanarcpro.brightowusu.com.cleanarcproj.di.components;

import android.content.BroadcastReceiver
import cleanarcpro.brightowusu.com.cleanarcproj.di.modules.*
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import com.squareup.picasso.Picasso
import dagger.Component
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.BroadcastChannel
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [
    RepositoryModule::class,
    NetworkModule::class,
    RetrofitModule::class,
    ImageFetcherModule::class,
    DatabaseModule::class
])
interface AppComponent {

    // Provides a handle on this below class, which can be used by other components
    fun getIUserRepository(): IUserRepository

    fun getRetrofit(): Retrofit

    fun getPicasso(): Picasso

    fun getNetworkChangeReceiver(): BroadcastReceiver

    @ExperimentalCoroutinesApi
    fun getNetworkBroadcastChannel(): BroadcastChannel<Boolean>
}
