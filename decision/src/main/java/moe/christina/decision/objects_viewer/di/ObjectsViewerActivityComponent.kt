package moe.christina.decision.objects_viewer.di

import dagger.Subcomponent
import dagger.android.AndroidInjector
import moe.christina.decision.objects_list.di.ObjectsListFragmentComponent
import moe.christina.decision.objects_viewer.ObjectsViewerActivity
import moe.christina.decision.objects_viewer.ObjectsViewerPresenter

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

    fun getObjectsViewerPresenter(): ObjectsViewerPresenter
}