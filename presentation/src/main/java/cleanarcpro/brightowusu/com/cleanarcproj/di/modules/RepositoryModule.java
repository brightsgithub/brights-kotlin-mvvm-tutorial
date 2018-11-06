package cleanarcpro.brightowusu.com.cleanarcproj.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import cleanarcpro.brightowusu.com.cleanarcproj.data.repository.UserRepositoryImpl;
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository;
import dagger.Module;
import dagger.Provides;

@Module//(includes = AppModule.class)
public class RepositoryModule {

    @Singleton
    @Provides
    public IUserRepository providesUserRepository() {
        return new UserRepositoryImpl();
    }

}
