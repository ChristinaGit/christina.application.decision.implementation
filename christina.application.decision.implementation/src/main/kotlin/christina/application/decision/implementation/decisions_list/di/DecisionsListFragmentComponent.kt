package christina.application.decision.implementation.decisions_list.di

import christina.application.decision.implementation.core.scope.FragmentScope
import christina.application.decision.implementation.decisions_list.DecisionsListFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@FragmentScope
@Subcomponent(modules = [
    DecisionsListFragmentUtilityModule::class,
    DecisionsListFragmentUseCaseModule::class,
    DecisionsListFragmentPresenterModule::class,
    DecisionsListFragmentPresenterBindsModule::class
])
interface DecisionsListFragmentComponent : AndroidInjector<DecisionsListFragment> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<DecisionsListFragment>() {
        abstract override fun build(): DecisionsListFragmentComponent
    }
}