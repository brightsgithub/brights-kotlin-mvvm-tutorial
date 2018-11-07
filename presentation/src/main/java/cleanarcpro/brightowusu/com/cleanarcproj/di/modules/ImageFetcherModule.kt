package cleanarcpro.brightowusu.com.cleanarcproj.di.modules

import android.content.Context
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [AppModule::class])
class ImageFetcherModule {

    @Provides
    @Singleton
    fun providesPicasso(context: Context): Picasso {
        return Picasso.with(context)
    }

}