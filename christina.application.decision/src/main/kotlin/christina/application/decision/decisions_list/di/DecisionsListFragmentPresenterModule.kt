package christina.application.decision.decisions_list.di

import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.android.FragmentEvent
import dagger.Binds
import dagger.Module
import christina.application.decision.decisions_list.DecisionsListFragment
import christina.application.decision.decisions_list.DecisionsListPresenter
import christina.application.decision.decisions_list.DecisionsListScreen
import christina.library.android.architecture.mvp.di.qualifier.ScopeName
import christina.library.android.architecture.mvp.di.scope.FragmentScope
import christina.library.android.architecture.mvp.presenter.ScreenObserver
import javax.inject.Named

@FragmentScope
@Module
interface DecisionsListFragmentPresenterModule {
    @FragmentScope
    @Binds
    fun provideLifecycleProvider(activity: DecisionsListFragment): LifecycleProvider<FragmentEvent>

    @Binds
    fun provideDecisionsListScreen(activity: DecisionsListFragment): DecisionsListScreen

    @FragmentScope
    @Named(ScopeName.FRAGMENT)
    @Binds
    fun provideDecisionsListScreenObserver(presenter: DecisionsListPresenter): ScreenObserver
}