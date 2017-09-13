package moe.christina.decision.decision.di

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import moe.christina.decision.decision.DecisionApplication
import moe.christina.decision.decisions_viewer.di.DecisionsViewerActivityComponent
import moe.christina.decision.objects_viewer.di.ObjectsViewerActivityComponent

@Component(modules = arrayOf(
    AndroidSupportInjectionModule::class,
    DecisionApplicationActivityFactoryModule::class))
interface DecisionApplicationComponent : AndroidInjector<DecisionApplication> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<DecisionApplication>()

    fun getObjectsViewerActivityComponentBuilder(): ObjectsViewerActivityComponent.Builder
    fun getDecisionsViewerActivityComponentBuilder(): DecisionsViewerActivityComponent.Builder
}