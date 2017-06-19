package moe.christina.decision.di.screen.module

import dagger.Module
import dagger.Provides
import moe.christina.decision.presenter.DecisionsViewerPresenter
import moe.christina.decision.screen.DecisionsViewerScreen
import moe.christina.mvp.Presenter
import moe.christina.mvp.di.scope.ScreenScope

@Module
@ScreenScope
class DecisionPresenterScreenModule {
    @Provides
    @ScreenScope
    fun provideDecisionsViewerPresenter(): Presenter<@JvmWildcard DecisionsViewerScreen> =
            DecisionsViewerPresenter()
}