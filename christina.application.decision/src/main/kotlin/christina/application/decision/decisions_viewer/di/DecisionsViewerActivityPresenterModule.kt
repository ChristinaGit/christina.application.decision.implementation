package christina.application.decision.decisions_viewer.di

import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.android.ActivityEvent
import dagger.Binds
import dagger.Module
import christina.application.decision.decisions_viewer.DecisionsViewerActivity
import christina.application.decision.decisions_viewer.DecisionsViewerPresenter
import christina.application.decision.decisions_viewer.DecisionsViewerScreen
import christina.library.android.architecture.mvp.di.qualifier.ScopeName
import christina.library.android.architecture.mvp.di.scope.ActivityScope
import christina.library.android.architecture.mvp.presenter.ScreenObserver
import javax.inject.Named

@ActivityScope
@Module
interface DecisionsViewerActivityPresenterModule {
    @ActivityScope
    @Binds
    fun provideLifecycleProvider(activity: DecisionsViewerActivity): LifecycleProvider<ActivityEvent>

    @Binds
    fun provideDecisionsViewerScreen(activity: DecisionsViewerActivity): DecisionsViewerScreen

    @ActivityScope
    @Named(ScopeName.ACTIVITY)
    @Binds
    fun provideDecisionsViewerScreenObserver(presenter: DecisionsViewerPresenter): ScreenObserver
}