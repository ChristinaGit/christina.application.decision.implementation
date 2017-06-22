package moe.christina.decision.di.screen

import dagger.Subcomponent
import moe.christina.decision.di.screen.module.DecisionPresenterScreenModule
import moe.christina.decision.di.subscreen.DecisionSubscreenComponent
import moe.christina.decision.di.subscreen.module.DecisionPresenterSubscreenModule
import moe.christina.decision.screen.activity.DecisionsViewerActivity
import moe.christina.mvp.di.scope.ScreenScope

@Subcomponent(modules = arrayOf(DecisionPresenterScreenModule::class))
@ScreenScope
interface DecisionScreenComponent {
    fun addDecisionSubscreenComponent(decisionPresenterSubscreenModule: DecisionPresenterSubscreenModule): DecisionSubscreenComponent

    fun inject(target: DecisionsViewerActivity)
}

