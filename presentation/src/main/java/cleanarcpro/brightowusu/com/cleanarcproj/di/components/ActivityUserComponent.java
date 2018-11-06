package cleanarcpro.brightowusu.com.cleanarcproj.di.components;

import cleanarcpro.brightowusu.com.cleanarcproj.di.ActivityScope;
import cleanarcpro.brightowusu.com.cleanarcproj.di.modules.ActivityUserModule;
import cleanarcpro.brightowusu.com.cleanarcproj.di.modules.InteractorsModule;
import cleanarcpro.brightowusu.com.cleanarcproj.di.modules.RepositoryModule;
import cleanarcpro.brightowusu.com.cleanarcproj.view.ActivityUser;
import dagger.Component;

@ActivityScope
//@Component(modules = {ActivityUserModule.class, InteractorsModule.class} )
public interface ActivityUserComponent {

    void inject(ActivityUser activityUser);
}
