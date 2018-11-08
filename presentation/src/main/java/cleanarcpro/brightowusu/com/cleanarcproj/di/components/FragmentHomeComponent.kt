package cleanarcpro.brightowusu.com.cleanarcproj.di.components

import cleanarcpro.brightowusu.com.cleanarcproj.di.ActivityScope
import cleanarcpro.brightowusu.com.cleanarcproj.di.modules.AppModule
import cleanarcpro.brightowusu.com.cleanarcproj.di.modules.FragmentHomeModule
import cleanarcpro.brightowusu.com.cleanarcproj.di.modules.InteractorsModule
import cleanarcpro.brightowusu.com.cleanarcproj.view.fragments.FragmentHome
import dagger.Component

@ActivityScope
@Component(
        dependencies =[AppComponent::class],
        modules = [AppModule::class, FragmentHomeModule::class, InteractorsModule::class] )
interface FragmentHomeComponent {

    fun inject(fragmentHome: FragmentHome)
}