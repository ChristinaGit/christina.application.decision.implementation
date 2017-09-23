package christina.application.decision.objects_viewer.di

import dagger.Subcomponent
import dagger.android.AndroidInjector
import christina.application.decision.objects_list.di.ObjectsListFragmentComponent
import christina.application.decision.objects_viewer.ObjectsViewerActivity
import christina.application.decision.objects_viewer.ObjectsViewerPresenter
import christina.library.android.architecture.mvp.di.scope.ActivityScope

@ActivityScope
@Subcomponent(modules = arrayOf(
    ObjectsViewerActivityFragmentFactoryModule::class,
    ObjectsViewerActivityViewerPresenterModule::class
))
interface ObjectsViewerActivityComponent : AndroidInjector<ObjectsViewerActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<ObjectsViewerActivity>() {
        abstract override fun build(): ObjectsViewerActivityComponent
    }

    fun getObjectsListFragmentComponentBuilder(): ObjectsListFragmentComponent.Builder

    @ActivityScope
    fun getObjectsViewerPresenter(): ObjectsViewerPresenter
}