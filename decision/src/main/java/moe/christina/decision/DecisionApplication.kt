package moe.christina.decision;

import android.app.Application
import android.support.annotation.CallSuper
import moe.christina.decision.di.application.DaggerDecisionApplicationComponent
import moe.christina.decision.di.application.DecisionApplicationComponent
import moe.christina.decision.di.application.DecisionApplicationComponentProvider

class DecisionApplication : Application(), DecisionApplicationComponentProvider {
    private lateinit var component: DecisionApplicationComponent

    override val decisionApplicationComponent: DecisionApplicationComponent
        get() = component

    @CallSuper
    override fun onCreate() {
        super.onCreate()

        component = DaggerDecisionApplicationComponent
                .builder()
                .build()
    }
}