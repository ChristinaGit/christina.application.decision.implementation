package christina.application.decision.decisions_viewer.di

import dagger.Subcomponent
import dagger.android.AndroidInjector
import christina.application.decision.decisions_list.di.DecisionsListFragmentComponent
import christina.application.decision.decisions_viewer.DecisionsViewerActivity
import christina.application.decision.decisions_viewer.DecisionsViewerPresenter
import christina.application.decision.decisions_viewer.domain.use_case.CreateDecisionInteractor
import christina.library.android.architecture.mvp.di.scope.ActivityScope

@ActivityScope
@Subcomponent(modules = arrayOf(
    DecisionsViewerActivityPresenterModule::class,
    DecisionsViewerActivityUtilityModule::class,
    DecisionsViewerActivityFragmentFactoryModule::class
))
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