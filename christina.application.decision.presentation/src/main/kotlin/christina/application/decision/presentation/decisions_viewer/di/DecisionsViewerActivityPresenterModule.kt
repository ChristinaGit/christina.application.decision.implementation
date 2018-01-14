package christina.application.decision.presentation.decisions_viewer.di

import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.android.ActivityEvent
import dagger.Binds
import dagger.Module
import christina.application.decision.presentation.decisions_viewer.DecisionsViewerActivity
import christina.application.decision.presentation.decisions_viewer.DecisionsViewerPresenter
import christina.application.decision.presentation.decisions_viewer.DecisionsViewerScreen
import christina.application.decision.presentation.core.qualifier.ScopeName
import christina.application.decision.presentation.core.scope.ActivityScope
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