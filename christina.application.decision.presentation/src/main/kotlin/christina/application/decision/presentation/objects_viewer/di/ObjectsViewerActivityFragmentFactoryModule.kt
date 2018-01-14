package christina.application.decision.presentation.objects_viewer.di

import android.support.v4.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap
import christina.application.decision.presentation.objects_list.ObjectsListFragment
import christina.application.decision.presentation.objects_list.di.ObjectsListFragmentComponent
import christina.application.decision.presentation.core.scope.ActivityScope

@ActivityScope
@Module
interface ObjectsViewerActivityFragmentFactoryModule {
    @Binds
    @IntoMap
    @FragmentKey(ObjectsListFragment::class)
    fun bindObjectsListFragmentInjectorFactory(builder: ObjectsListFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>
}