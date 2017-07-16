package moe.christina.decision.di.application

import dagger.Module
import dagger.android.ContributesAndroidInjector
import moe.christina.decision.di.activity.DecisionFragmentProviderModule
import moe.christina.decision.di.activity.DecisionViewerModule
import moe.christina.decision.screen.activity.DecisionsViewerActivity
import moe.christina.mvp.di.scope.ActivityScope
import moe.christina.mvp.di.scope.ApplicationScope

@Module
@ApplicationScope
abstract class DecisionActivityProviderModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = arrayOf(
        DecisionViewerModule::class,
        DecisionFragmentProviderModule::class
    ))
    abstract fun bindDecisionsViewerActivity(): DecisionsViewerActivity
}