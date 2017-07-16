package moe.christina.decision.di.activity

import dagger.Module
import dagger.android.ContributesAndroidInjector
import moe.christina.decision.di.fragment.DecisionsListModule
import moe.christina.decision.screen.fragment.DecisionsListFragment
import moe.christina.mvp.di.scope.ActivityScope
import moe.christina.mvp.di.scope.FragmentScope

@Module
@ActivityScope
abstract class DecisionFragmentProviderModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = arrayOf(
        DecisionsListModule::class
    ))
    abstract fun bindDecisionsListFragment(): DecisionsListFragment
}