package christina.application.decision.implementation.objects_viewer.di

import christina.application.decision.implementation.core.scope.ActivityScope
import christina.application.decision.implementation.objects_viewer.ObjectsViewerActivity
import christina.application.decision.presentation.objects_viewer.ObjectsViewerScreen
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.android.ActivityEvent
import dagger.Binds
import dagger.Module

@ActivityScope
@Module
interface ObjectsViewerActivityPresenterBindsModule {
    @ActivityScope
    @Binds
    fun provideLifecycleProvider(activity: ObjectsViewerActivity): LifecycleProvider<ActivityEvent>

    @Binds
    fun provideObjectsViewerScreen(activity: ObjectsViewerActivity): ObjectsViewerScreen
}