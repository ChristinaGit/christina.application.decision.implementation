package christina.application.decision.implementation.objects_viewer.di

import android.support.v4.app.Fragment
import christina.application.decision.implementation.core.scope.ActivityScope
import christina.application.decision.implementation.objects_list.ObjectsListFragment
import christina.application.decision.implementation.objects_list.di.ObjectsListFragmentComponent
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@ActivityScope
@Module
interface ObjectsViewerActivityFragmentFactoryModule {
    @Binds
    @IntoMap
    @FragmentKey(ObjectsListFragment::class)
    fun bindObjectsListFragmentInjectorFactory(builder: ObjectsListFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>
}