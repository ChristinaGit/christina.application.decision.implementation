package moe.christina.decision.screen.fragment

import android.os.Bundle
import android.support.annotation.CallSuper
import moe.christina.decision.di.screen.DecisionScreenComponent
import moe.christina.decision.di.screen.DecisionScreenComponentProvider
import moe.christina.decision.di.subscreen.DecisionSubscreenComponent
import moe.christina.decision.di.subscreen.DecisionSubscreenComponentProvider
import moe.christina.decision.di.subscreen.module.DecisionPresenterSubscreenModule
import moe.christina.mvp.android.view.support.ScreenFragment

abstract class BaseDecisionFragment : ScreenFragment(), DecisionSubscreenComponentProvider {
    final override val decisionSubscreenComponent: DecisionSubscreenComponent
        get() = component

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        component = screenComponent.addDecisionSubscreenComponent(DecisionPresenterSubscreenModule())

        super.onCreate(savedInstanceState)
    }

    private lateinit var component: DecisionSubscreenComponent

    private val screenComponent: DecisionScreenComponent
        get() {
            val activity = activity
            if (activity !is DecisionScreenComponentProvider) {
                throw IllegalStateException("The activity must implement ${DecisionScreenComponentProvider::class}")
            }

            return activity.decisionScreenComponent
        }
}