package christina.application.decision.implementation.decisions_viewer.di

import christina.application.decision.implementation.core.scope.ActivityScope
import christina.application.decision.presentation.decisions_viewer.DecisionsViewerScreen
import christina.application.decision.implementation.decisions_viewer.DecisionsViewerActivity
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.android.ActivityEvent
import dagger.Binds
import dagger.Module

@ActivityScope
@Module
interface DecisionsViewerActivityPresenterBindsModule {
    @ActivityScope
    @Binds
    fun provideLifecycleProvider(activity: DecisionsViewerActivity): LifecycleProvider<ActivityEvent>

    @Binds
    fun provideDecisionsViewerScreen(activity: DecisionsViewerActivity): DecisionsViewerScreen
}