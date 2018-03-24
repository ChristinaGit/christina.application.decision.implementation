package christina.application.decision.implementation.decisions_list.di

import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.android.FragmentEvent
import dagger.Module
import dagger.Provides
import christina.library.android.common.rx.AndroidRxManager
import christina.library.application.rx.RxManager
import christina.application.decision.implementation.core.qualifier.ScopeName
import christina.application.decision.implementation.core.scope.FragmentScope
import javax.inject.Named

@FragmentScope
@Module
class DecisionsListFragmentUtilityModule {
    @FragmentScope
    @Named(ScopeName.FRAGMENT)
    @Provides
    fun provideRxManager(lifecycleProvider: LifecycleProvider<FragmentEvent>): RxManager = AndroidRxManager(lifecycleProvider)
}