package cleanarcpro.brightowusu.com.cleanarcproj.di.components;

import cleanarcpro.brightowusu.com.cleanarcproj.di.modules.ImageFetcherModule
import cleanarcpro.brightowusu.com.cleanarcproj.di.modules.NetworkModule
import cleanarcpro.brightowusu.com.cleanarcproj.di.modules.RepositoryModule
import cleanarcpro.brightowusu.com.cleanarcproj.di.modules.RetrofitModule
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository
import com.squareup.picasso.Picasso
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class, NetworkModule::class, RetrofitModule::class, ImageFetcherModule::class])
interface AppComponent {

    // Provides a handle on this below class, which can be used by other components
    fun getIUserRepository() : IUserRepository

    fun getRetrofit() : Retrofit

    fun getPicasso() : Picasso
}
