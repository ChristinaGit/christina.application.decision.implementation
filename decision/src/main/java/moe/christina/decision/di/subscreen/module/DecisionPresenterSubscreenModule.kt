package moe.christina.decision.di.subscreen.module

import dagger.Module
import dagger.Provides
import moe.christina.decision.di.qualifier.ScreenName
import moe.christina.decision.presenter.DecisionsListPresenter
import moe.christina.decision.screen.DecisionsListScreen
import moe.christina.mvp.di.scope.SubscreenScope
import moe.christina.mvp.presenter.Presenter
import javax.inject.Named

@Module
@SubscreenScope
class DecisionPresenterSubscreenModule {
    @Named(ScreenName.DECISIONS_LIST)
    @Provides
    @SubscreenScope
    fun provideDecisionsListPresenter(): Presenter<@JvmWildcard DecisionsListScreen> =
            DecisionsListPresenter()
}