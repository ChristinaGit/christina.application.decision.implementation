package moe.christina.decision.decisions_viewer.di

import dagger.Subcomponent
import dagger.android.AndroidInjector
import moe.christina.decision.decisions_viewer.DecisionsViewerActivity
import moe.christina.decision.decisions_viewer.DecisionsViewerPresenter
import moe.christina.decision.decisions_viewer.domain.usecase.CreateDecisionInteractor

@Subcomponent(modules = arrayOf(
    DecisionsViewerActivityPresenterModule::class
))
interface DecisionsViewerActivityComponent : AndroidInjector<DecisionsViewerActivity> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<DecisionsViewerActivity>()

    fun getDecisionsViewerPresenter(): DecisionsViewerPresenter

    fun getCreateDecisionInteractor(): CreateDecisionInteractor
}