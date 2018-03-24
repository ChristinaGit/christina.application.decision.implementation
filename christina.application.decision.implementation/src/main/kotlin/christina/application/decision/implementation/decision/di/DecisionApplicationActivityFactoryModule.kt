package christina.application.decision.implementation.decision.di

import android.app.Activity
import dagger.Binds
import dagger.Module
import dagger.android.ActivityKey
import dagger.android.AndroidInjector
import dagger.multibindings.IntoMap
import christina.application.decision.implementation.decisions_viewer.DecisionsViewerActivity
import christina.application.decision.implementation.decisions_viewer.di.DecisionsViewerActivityComponent
import christina.application.decision.implementation.objects_viewer.ObjectsViewerActivity
import christina.application.decision.implementation.objects_viewer.di.ObjectsViewerActivityComponent
import christina.application.decision.implementation.core.scope.ApplicationScope

@ApplicationScope
@Module
interface DecisionApplicationActivityFactoryModule {
    @Binds
    @IntoMap
    @ActivityKey(ObjectsViewerActivity::class)
    fun bindObjectsViewerActivityInjectorFactory(builder: ObjectsViewerActivityComponent.Builder): AndroidInjector.Factory<out Activity>

    @Binds
    @IntoMap
    @ActivityKey(DecisionsViewerActivity::class)
    fun bindDecisionsViewerActivityInjectorFactory(builder: DecisionsViewerActivityComponent.Builder): AndroidInjector.Factory<out Activity>
}