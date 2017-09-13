package moe.christina.decision.decisions_viewer.di

import dagger.Binds
import dagger.Module
import moe.christina.decision.decisions_viewer.DecisionsViewerActivity
import moe.christina.decision.decisions_viewer.DecisionsViewerPresenter
import moe.christina.decision.decisions_viewer.DecisionsViewerScreen
import moe.christina.mvp.presenter.Presenter

@Module
abstract class DecisionsViewerActivityPresenterModule {
    @Binds
    abstract fun provideDecisionsViewerScreen(activity: DecisionsViewerActivity): DecisionsViewerScreen

    @Binds
    abstract fun provideDecisionsViewerPresenter(presenter: DecisionsViewerPresenter): Presenter<@JvmWildcard DecisionsViewerScreen>
}