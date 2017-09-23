package christina.application.decision.decision.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import christina.application.decision.decision.DecisionApplication
import christina.application.decision.decisions_viewer.di.DecisionsViewerActivityComponent
import christina.application.decision.objects_viewer.di.ObjectsViewerActivityComponent
import christina.library.android.architecture.mvp.di.scope.ApplicationScope

@ApplicationScope
@Component(modules = arrayOf(
    AndroidSupportInjectionModule::class,
    DecisionApplicationActivityFactoryModule::class,
    DecisionApplicationPersistenceModule::class))
interface DecisionApplicationComponent : AndroidInjector<DecisionApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<DecisionApplication>() {
        abstract override fun build(): DecisionApplicationComponent
    }

    fun getObjectsViewerActivityComponentBuilder(): ObjectsViewerActivityComponent.Builder

    fun getDecisionsViewerActivityComponentBuilder(): DecisionsViewerActivityComponent.Builder
}