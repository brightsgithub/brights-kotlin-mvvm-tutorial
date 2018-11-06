package cleanarcpro.brightowusu.com.cleanarcproj.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module//(includes = {RepositoryModule.class, NetworkModule.class, InteractorsModule.class})
public class AppModule {

    public Context myApplication;

    public AppModule(Context myApplication) {
        this.myApplication = myApplication;
    }

    @Provides
    @Singleton
    public Context providesApplication() {
        return myApplication;
    }
}
