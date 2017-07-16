package moe.christina.decision.di.fragment

import dagger.Module
import dagger.Provides
import moe.christina.decision.presenter.DecisionsListPresenter
import moe.christina.decision.screen.DecisionsListScreen
import moe.christina.mvp.di.scope.FragmentScope
import moe.christina.mvp.presenter.Presenter

@Module
@FragmentScope
class DecisionsListModule {
    @Provides
    @FragmentScope
    fun provideDecisionsViewerPresenter(): Presenter<@JvmWildcard DecisionsListScreen> =
        DecisionsListPresenter()
}