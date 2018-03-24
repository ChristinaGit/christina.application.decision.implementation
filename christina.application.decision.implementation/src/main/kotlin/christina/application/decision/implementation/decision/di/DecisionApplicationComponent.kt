package christina.application.decision.implementation.decision.di

import christina.application.decision.implementation.core.scope.ApplicationScope
import christina.application.decision.implementation.decision.DecisionApplication
import christina.application.decision.implementation.decisions_viewer.di.DecisionsViewerActivityComponent
import christina.application.decision.implementation.objects_viewer.di.ObjectsViewerActivityComponent
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

@ApplicationScope
@Component(modules = [
    AndroidSupportInjectionModule::class,
    DecisionApplicationActivityFactoryModule::class,
    DecisionApplicationPersistenceModule::class
])
interface DecisionApplicationComponent : AndroidInjector<DecisionApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<DecisionApplication>() {
        abstract override fun build(): DecisionApplicationComponent
    }

    fun getObjectsViewerActivityComponentBuilder(): ObjectsViewerActivityComponent.Builder

    fun getDecisionsViewerActivityComponentBuilder(): DecisionsViewerActivityComponent.Builder
}