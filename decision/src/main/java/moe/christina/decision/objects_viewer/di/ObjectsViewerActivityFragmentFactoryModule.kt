package moe.christina.decision.objects_viewer.di

import android.support.v4.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap
import moe.christina.decision.objects_list.ObjectsListFragment
import moe.christina.decision.objects_list.di.ObjectsListFragmentComponent

@Module
internal abstract class ObjectsViewerActivityFragmentFactoryModule {
    @Binds
    @IntoMap
    @FragmentKey(ObjectsListFragment::class)
    internal abstract fun bindObjectsListFragmentInjectorFactory(builder: ObjectsListFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>
}