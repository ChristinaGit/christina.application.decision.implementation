package christina.application.decision.implementation.decisions_list.di

import christina.application.decision.domain.use_case.decision.get_list.GetDecisionsListInteractor
import christina.application.decision.implementation.core.qualifier.ScopeName
import christina.application.decision.implementation.core.scope.FragmentScope
import christina.application.decision.presentation.decisions_list.DecisionsListPresenter
import christina.application.decision.presentation.decisions_list.DecisionsListScreen
import christina.library.android.architecture.mvp.presenter.ScreenObserver
import dagger.Module
import dagger.Provides
import javax.inject.Named

@FragmentScope
@Module
class DecisionsListFragmentPresenterModule {
    @FragmentScope
    @Named(ScopeName.FRAGMENT)
    @Provides
    fun provideDecisionsViewerScreenObserver(
        screen: DecisionsListScreen,
        getDecisionsListInteractor: GetDecisionsListInteractor
    ): ScreenObserver =
        DecisionsListPresenter(
            screen,
            getDecisionsListInteractor)
}