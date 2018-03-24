package christina.application.decision.implementation.decisions_viewer.di

import christina.application.decision.implementation.core.qualifier.ScopeName
import christina.application.decision.implementation.core.scope.ActivityScope
import christina.application.decision.implementation.decisions_viewer.AndroidDecisionsViewerLocalisationManager
import christina.application.decision.implementation.decisions_viewer.DecisionsViewerActivity
import christina.application.decision.presentation.decisions_viewer.DecisionsViewerLocalisationManager
import christina.library.android.common.rx.AndroidRxManager
import christina.library.application.rx.RxManager
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.android.ActivityEvent
import dagger.Module
import dagger.Provides
import javax.inject.Named

@ActivityScope
@Module
class DecisionsViewerActivityUtilityModule {
    @ActivityScope
    @Named(ScopeName.ACTIVITY)
    @Provides
    fun provideRxManager(lifecycleProvider: LifecycleProvider<ActivityEvent>): RxManager =
        AndroidRxManager(lifecycleProvider)

    @ActivityScope
    @Provides
    fun provideLocalizationManager(activity: DecisionsViewerActivity): DecisionsViewerLocalisationManager =
        AndroidDecisionsViewerLocalisationManager(activity)
}