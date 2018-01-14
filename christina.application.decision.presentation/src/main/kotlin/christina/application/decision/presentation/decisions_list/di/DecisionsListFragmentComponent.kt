package christina.application.decision.presentation.decisions_list.di

import christina.application.decision.presentation.core.scope.FragmentScope
import christina.application.decision.presentation.decisions_list.DecisionsListFragment
import christina.application.decision.presentation.decisions_list.DecisionsListPresenter
import dagger.Subcomponent
import dagger.android.AndroidInjector

@FragmentScope
@Subcomponent(modules = [
    DecisionsListFragmentUtilityModule::class,
    DecisionsListFragmentUseCaseModule::class,
    DecisionsListFragmentPresenterModule::class
])
interface DecisionsListFragmentComponent : AndroidInjector<DecisionsListFragment> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<DecisionsListFragment>() {
        abstract override fun build(): DecisionsListFragmentComponent
    }

    @FragmentScope
    fun getDecisionsListPresenter(): DecisionsListPresenter
}