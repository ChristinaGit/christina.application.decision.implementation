package christina.application.decision.implementation.objects_viewer.di

import christina.application.decision.implementation.core.scope.ActivityScope
import christina.application.decision.implementation.objects_list.di.ObjectsListFragmentComponent
import christina.application.decision.implementation.objects_viewer.ObjectsViewerActivity
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ActivityScope
@Subcomponent(modules = [
    ObjectsViewerActivityFragmentFactoryModule::class,
    ObjectsViewerActivityPresenterModule::class,
    ObjectsViewerActivityPresenterBindsModule::class
])
interface ObjectsViewerActivityComponent : AndroidInjector<ObjectsViewerActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<ObjectsViewerActivity>() {
        abstract override fun build(): ObjectsViewerActivityComponent
    }

    fun getObjectsListFragmentComponentBuilder(): ObjectsListFragmentComponent.Builder
}