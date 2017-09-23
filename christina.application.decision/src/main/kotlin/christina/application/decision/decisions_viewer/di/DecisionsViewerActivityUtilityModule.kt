package christina.application.decision.decisions_viewer.di

import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.android.ActivityEvent
import dagger.Module
import dagger.Provides
import christina.library.android.common.rx.AndroidRxManager
import christina.library.android.common.rx.RxManager
import christina.library.android.architecture.mvp.di.qualifier.ScopeName
import christina.library.android.architecture.mvp.di.scope.ActivityScope
import javax.inject.Named

@ActivityScope
@Module
class DecisionsViewerActivityUtilityModule {
    @ActivityScope
    @Named(ScopeName.ACTIVITY)
    @Provides
    fun provideRxManager(lifecycleProvider: LifecycleProvider<ActivityEvent>): RxManager =
        AndroidRxManager(lifecycleProvider)
}