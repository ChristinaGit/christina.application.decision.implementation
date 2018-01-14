package christina.application.decision.presentation.decisions_viewer.di

import christina.application.decision.domain.use_case.decision.create.CreateDecisionInteractor
import christina.application.decision.presentation.core.scope.ActivityScope
import christina.application.decision.presentation.decisions_list.di.DecisionsListFragmentComponent
import christina.application.decision.presentation.decisions_viewer.DecisionsViewerActivity
import christina.application.decision.presentation.decisions_viewer.DecisionsViewerPresenter
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ActivityScope
@Subcomponent(modules = [
    DecisionsViewerActivityPresenterModule::class,
    DecisionsViewerActivityUtilityModule::class,
    DecisionsViewerActivityUseCaseModule::class,
    DecisionsViewerActivityFragmentFactoryModule::class
])
interface DecisionsViewerActivityComponent : AndroidInjector<DecisionsViewerActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<DecisionsViewerActivity>() {
        abstract override fun build(): DecisionsViewerActivityComponent
    }

    fun getDecisionsListFragmentComponentBuilder(): DecisionsListFragmentComponent.Builder

    @ActivityScope
    fun getDecisionsViewerPresenter(): DecisionsViewerPresenter

    @ActivityScope
    fun getCreateDecisionInteractor(): CreateDecisionInteractor
}