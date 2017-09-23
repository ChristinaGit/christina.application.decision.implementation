package christina.application.decision.objects_viewer.di

import dagger.Binds
import dagger.Module
import christina.application.decision.objects_viewer.ObjectsViewerActivity
import christina.application.decision.objects_viewer.ObjectsViewerPresenter
import christina.application.decision.objects_viewer.ObjectsViewerScreen
import christina.library.android.architecture.mvp.di.qualifier.ScopeName
import christina.library.android.architecture.mvp.di.scope.ActivityScope
import christina.library.android.architecture.mvp.presenter.ScreenObserver
import javax.inject.Named

@ActivityScope
@Module
abstract class ObjectsViewerActivityViewerPresenterModule {
    @Binds
    abstract fun provideObjectsViewerScreen(presenter: ObjectsViewerActivity): ObjectsViewerScreen

    @ActivityScope
    @Named(ScopeName.ACTIVITY)
    @Binds
    abstract fun provideObjectsViewerScreenObserver(presenter: ObjectsViewerPresenter): ScreenObserver
}