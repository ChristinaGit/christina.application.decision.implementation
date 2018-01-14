package christina.application.decision.presentation.objects_viewer.di

import dagger.Binds
import dagger.Module
import christina.application.decision.presentation.objects_viewer.ObjectsViewerActivity
import christina.application.decision.presentation.objects_viewer.ObjectsViewerPresenter
import christina.application.decision.presentation.objects_viewer.ObjectsViewerScreen
import christina.application.decision.presentation.core.qualifier.ScopeName
import christina.application.decision.presentation.core.scope.ActivityScope
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