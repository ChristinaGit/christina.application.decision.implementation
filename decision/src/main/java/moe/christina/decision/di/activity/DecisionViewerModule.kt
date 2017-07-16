package moe.christina.decision.di.activity

import dagger.Module
import dagger.Provides
import moe.christina.decision.presenter.DecisionsViewerPresenter
import moe.christina.decision.screen.DecisionsViewerScreen
import moe.christina.mvp.di.scope.ActivityScope
import moe.christina.mvp.presenter.Presenter

@Module
@ActivityScope
class DecisionViewerModule {
    @Provides
    @ActivityScope
    fun provideDecisionsViewerPresenter(): Presenter<@JvmWildcard DecisionsViewerScreen> =
        DecisionsViewerPresenter()
}