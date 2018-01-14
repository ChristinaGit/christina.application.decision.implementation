package christina.application.decision.presentation.objects_viewer.di

import christina.application.decision.presentation.core.scope.ActivityScope
import christina.application.decision.presentation.objects_list.di.ObjectsListFragmentComponent
import christina.application.decision.presentation.objects_viewer.ObjectsViewerActivity
import christina.application.decision.presentation.objects_viewer.ObjectsViewerPresenter
import dagger.Subcomponent
import dagger.android.AndroidInjector

@ActivityScope
@Subcomponent(modules = [
    ObjectsViewerActivityFragmentFactoryModule::class,
    ObjectsViewerActivityViewerPresenterModule::class
])
interface ObjectsViewerActivityComponent : AndroidInjector<ObjectsViewerActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<ObjectsViewerActivity>() {
        abstract override fun build(): ObjectsViewerActivityComponent
    }

    fun getObjectsListFragmentComponentBuilder(): ObjectsListFragmentComponent.Builder

    @ActivityScope
    fun getObjectsViewerPresenter(): ObjectsViewerPresenter
}