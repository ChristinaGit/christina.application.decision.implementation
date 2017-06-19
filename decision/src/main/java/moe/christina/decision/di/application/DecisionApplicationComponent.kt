package moe.christina.decision.di.application

import dagger.Component
import moe.christina.decision.di.screen.module.DecisionPresenterScreenModule
import moe.christina.decision.di.screen.DecisionScreenComponent
import moe.christina.mvp.di.scope.ApplicationScope

@Component
@ApplicationScope
interface DecisionApplicationComponent {
    fun addDecisionScreenComponent(decisionPresenterScreenModule: DecisionPresenterScreenModule): DecisionScreenComponent
}