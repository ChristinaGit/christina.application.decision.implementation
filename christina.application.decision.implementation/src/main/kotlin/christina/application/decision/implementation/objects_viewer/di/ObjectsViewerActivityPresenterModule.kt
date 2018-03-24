package christina.application.decision.implementation.objects_viewer.di

import christina.application.decision.implementation.core.qualifier.ScopeName
import christina.application.decision.implementation.core.scope.ActivityScope
import christina.application.decision.presentation.objects_viewer.ObjectsViewerPresenter
import christina.application.decision.presentation.objects_viewer.ObjectsViewerScreen
import christina.library.android.architecture.mvp.presenter.ScreenObserver
import dagger.Module
import dagger.Provides
import javax.inject.Named

@ActivityScope
@Module
abstract class ObjectsViewerActivityPresenterModule {
    @ActivityScope
    @Named(ScopeName.ACTIVITY)
    @Provides
    fun provideObjectsViewerScreenObserver(
        screen: ObjectsViewerScreen
    ): ScreenObserver = ObjectsViewerPresenter(screen)
}