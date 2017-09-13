package moe.christina.decision.objects_viewer.di

import dagger.Binds
import dagger.Module
import moe.christina.decision.objects_viewer.ObjectsViewerActivity
import moe.christina.decision.objects_viewer.ObjectsViewerPresenter
import moe.christina.decision.objects_viewer.ObjectsViewerScreen
import moe.christina.mvp.presenter.Presenter

@Module
abstract class ObjectsViewerActivityViewerPresenterModule {
    @Binds
    abstract fun provideObjectsViewerScreen(presenter: ObjectsViewerActivity): ObjectsViewerScreen

    @Binds
    abstract fun provideObjectsViewerPresenter(presenter: ObjectsViewerPresenter): Presenter<@JvmWildcard ObjectsViewerScreen>
}