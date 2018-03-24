package christina.application.decision.implementation.decisions_list.di

import christina.application.decision.implementation.core.scope.FragmentScope
import christina.application.decision.implementation.decisions_list.DecisionsListFragment
import christina.application.decision.presentation.decisions_list.DecisionsListScreen
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.android.FragmentEvent
import dagger.Binds
import dagger.Module

@FragmentScope
@Module
interface DecisionsListFragmentPresenterBindsModule {
    @FragmentScope
    @Binds
    fun provideLifecycleProvider(activity: DecisionsListFragment): LifecycleProvider<FragmentEvent>

    @Binds
    fun provideDecisionsListScreen(activity: DecisionsListFragment): DecisionsListScreen
}