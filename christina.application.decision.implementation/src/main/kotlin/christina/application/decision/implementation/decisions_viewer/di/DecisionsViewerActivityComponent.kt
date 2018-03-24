package christina.application.decision.implementation.decisions_viewer.di

import christina.application.decision.implementation.core.scope.ActivityScope
import christina.application.decision.implementation.decisions_list.di.DecisionsListFragmentComponent
import christina.application.decision.implementation.decisions_viewer.DecisionsViewerActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ActivityScope
@Subcomponent(modules = [
    DecisionsViewerActivityPresenterModule::class,
    DecisionsViewerActivityPresenterBindsModule::class,
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
}