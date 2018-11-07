package cleanarcpro.brightowusu.com.cleanarcproj.di.components

import cleanarcpro.brightowusu.com.cleanarcproj.di.ActivityScope
import cleanarcpro.brightowusu.com.cleanarcproj.di.modules.AppModule
import cleanarcpro.brightowusu.com.cleanarcproj.di.modules.FragmentPastExpModule
import cleanarcpro.brightowusu.com.cleanarcproj.di.modules.InteractorsModule
import cleanarcpro.brightowusu.com.cleanarcproj.view.fragments.FragmentPreviousExperiences
import dagger.Component

@ActivityScope
@Component(
        dependencies =[AppComponent::class],
        modules = [AppModule::class, FragmentPastExpModule::class, InteractorsModule::class] )
interface FragmentPastExpComponent {

    fun inject(fragment: FragmentPreviousExperiences)
}