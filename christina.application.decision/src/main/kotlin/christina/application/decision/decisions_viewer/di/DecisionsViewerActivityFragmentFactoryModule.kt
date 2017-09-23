package christina.application.decision.decisions_viewer.di

import android.support.v4.app.Fragment
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjector
import dagger.android.support.FragmentKey
import dagger.multibindings.IntoMap
import christina.application.decision.decisions_list.DecisionsListFragment
import christina.application.decision.decisions_list.di.DecisionsListFragmentComponent
import christina.library.android.architecture.mvp.di.scope.ActivityScope

@ActivityScope
@Module
interface DecisionsViewerActivityFragmentFactoryModule {
    @Binds
    @IntoMap
    @FragmentKey(DecisionsListFragment::class)
    fun bindDecisionsListFragmentInjectorFactory(builder: DecisionsListFragmentComponent.Builder): AndroidInjector.Factory<out Fragment>
}