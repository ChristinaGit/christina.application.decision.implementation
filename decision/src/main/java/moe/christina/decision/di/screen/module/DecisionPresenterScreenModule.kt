package moe.christina.decision.di.screen.module

import dagger.Module
import dagger.Provides
import moe.christina.decision.di.qualifier.ScreenName
import moe.christina.decision.presenter.DecisionsViewerPresenter
import moe.christina.decision.screen.DecisionsViewerScreen
import moe.christina.mvp.di.scope.ScreenScope
import moe.christina.mvp.presenter.Presenter
import javax.inject.Named

@Module
@ScreenScope
class DecisionPresenterScreenModule {
    @Named(ScreenName.DECISIONS_VIEWER)
    @Provides
    @ScreenScope
    fun provideDecisionsViewerPresenter(): Presenter<@JvmWildcard DecisionsViewerScreen> =
        DecisionsViewerPresenter()
}