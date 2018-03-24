package christina.application.decision.implementation.decisions_viewer.di

import android.support.v4.app.Fragment
import christina.application.decision.implementation.core.scope.ActivityScope
import christina.application.decision.implementation.decisions_list.DecisionsListFragment
import christina.application.decision.implementation.decisions_list.di.DecisionsListFragmentComponent
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap

@ActivityScope
@Module
interface DecisionsViewerActivityFragmentFactoryModule {
    @Binds
    @IntoMap
    @FragmentKey(DecisionsListFragment::class)
    fun bindDecisionsListFragmentInjectorFactory(builder: DecisionsListFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>
}