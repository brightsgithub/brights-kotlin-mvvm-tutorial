package cleanarcpro.brightowusu.com.cleanarcproj.di.components;

import javax.inject.Singleton;

import cleanarcpro.brightowusu.com.cleanarcproj.di.modules.AppModule;
import cleanarcpro.brightowusu.com.cleanarcproj.di.modules.InteractorsModule;
import cleanarcpro.brightowusu.com.cleanarcproj.di.modules.NetworkModule;
import cleanarcpro.brightowusu.com.cleanarcproj.di.modules.RepositoryModule;
import cleanarcpro.brightowusu.com.cleanarcproj.domain.abstractions.repository.IUserRepository;
import dagger.Component;
import okhttp3.OkHttpClient;

@Singleton
@Component(modules = {RepositoryModule.class, NetworkModule.class})
public interface AppComponent {

    OkHttpClient getOKOkHttpClient();

    IUserRepository getIUserRepository();

}
