package moe.christina.decision.decision.di

import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap
import moe.christina.decision.decisions_viewer.DecisionsViewerActivity
import moe.christina.decision.decisions_viewer.di.DecisionsViewerActivityComponent
import moe.christina.decision.objects_viewer.ObjectsViewerActivity
import moe.christina.decision.objects_viewer.di.ObjectsViewerActivityComponent

@Module
abstract class DecisionApplicationActivityFactoryModule {
    @Binds
    @IntoMap
    @ActivityKey(ObjectsViewerActivity::class)
    internal abstract fun bindObjectsViewerActivityInjectorFactory(builder: ObjectsViewerActivityComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(DecisionsViewerActivity::class)
    internal abstract fun bindDecisionsViewerActivityInjectorFactory(builder: DecisionsViewerActivityComponent.Builder): AndroidInjector.Factory<out Activity>
}