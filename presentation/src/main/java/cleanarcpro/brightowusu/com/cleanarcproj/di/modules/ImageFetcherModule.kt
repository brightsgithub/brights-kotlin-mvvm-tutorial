package cleanarcpro.brightowusu.com.cleanarcproj.di.modules

import android.content.Context
import com.squareup.picasso.Picasso
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Image fetcher.
 *
 * Created by Bright Owusu-Amankwaa
 */
@Module(includes = [AppModule::class])
class ImageFetcherModule {

    @Provides
    @Singleton
    fun providesPicasso(context: Context): Picasso {
        return Picasso.with(context)
    }

}