package cleanarcpro.brightowusu.com.cleanarcproj.di.components;

import cleanarcpro.brightowusu.com.cleanarcproj.di.ActivityScope;
import cleanarcpro.brightowusu.com.cleanarcproj.di.modules.ActivityUserModule;
import cleanarcpro.brightowusu.com.cleanarcproj.di.modules.AppModule;
import cleanarcpro.brightowusu.com.cleanarcproj.di.modules.InteractorsModule;
import cleanarcpro.brightowusu.com.cleanarcproj.di.modules.RepositoryModule;
import cleanarcpro.brightowusu.com.cleanarcproj.view.ActivityUser;
import dagger.Component;

@ActivityScope
@Component(
        dependencies =[AppComponent::class],
        modules = [AppModule::class, ActivityUserModule::class, InteractorsModule::class] )
interface ActivityUserComponent {

    fun inject(activityUser: ActivityUser)
}
