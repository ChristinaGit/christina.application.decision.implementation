package christina.application.decision.implementation.decisions_viewer.di

import christina.application.decision.domain.use_case.decision.create.CreateDecisionInteractor
import christina.application.decision.implementation.core.qualifier.ScopeName
import christina.application.decision.implementation.core.scope.ActivityScope
import christina.application.decision.presentation.decisions_viewer.DecisionsViewerLocalisationManager
import christina.application.decision.presentation.decisions_viewer.DecisionsViewerPresenter
import christina.application.decision.presentation.decisions_viewer.DecisionsViewerScreen
import christina.library.android.architecture.mvp.presenter.ScreenObserver
import dagger.Module
import dagger.Provides
import javax.inject.Named

@ActivityScope
@Module
class DecisionsViewerActivityPresenterModule {
    @ActivityScope
    @Named(ScopeName.ACTIVITY)
    @Provides
    fun provideDecisionsViewerScreenObserver(
        screen: DecisionsViewerScreen,
        localizationManager: DecisionsViewerLocalisationManager,
        createDecisionInteractor: CreateDecisionInteractor
    ): ScreenObserver =
        DecisionsViewerPresenter(
            screen,
            localizationManager,
            createDecisionInteractor)
}