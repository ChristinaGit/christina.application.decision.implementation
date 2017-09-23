package christina.application.decision.decisions_list.di

import dagger.Subcomponent
import dagger.android.AndroidInjector
import christina.application.decision.decisions_list.DecisionsListFragment
import christina.application.decision.decisions_list.DecisionsListPresenter
import christina.application.decision.decisions_list.domain.use_case.GetDecisionsInteractor
import christina.library.android.architecture.mvp.di.scope.FragmentScope

@FragmentScope
@Subcomponent(modules = arrayOf(
    DecisionsListFragmentUtilityModule::class,
    DecisionsListFragmentPresenterModule::class
))
interface DecisionsListFragmentComponent : AndroidInjector<DecisionsListFragment> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<DecisionsListFragment>() {
        abstract override fun build(): DecisionsListFragmentComponent
    }

    @FragmentScope
    fun getDecisionsListPresenter(): DecisionsListPresenter

    @FragmentScope
    fun getGetDecisionsInteractor(): GetDecisionsInteractor
}