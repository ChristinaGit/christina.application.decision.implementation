package moe.christina.decision.core

import android.support.annotation.CallSuper
import moe.christina.decision.di.application.DecisionApplicationComponent
import moe.christina.decision.di.application.DecisionApplicationComponentProvider
import moe.christina.decision.di.screen.DecisionScreenComponent
import moe.christina.decision.di.screen.DecisionScreenComponentProvider
import moe.christina.decision.di.screen.module.DecisionPresenterScreenModule
import moe.christina.mvp.android.view.ScreenActivity

abstract class BaseDecisionActivity : ScreenActivity(), DecisionScreenComponentProvider {
    final override val decisionScreenComponent: DecisionScreenComponent
        get() = component

    @CallSuper
    override fun onInjectMembers() {
        super.onInjectMembers()

        component = applicationComponent.addDecisionScreenComponent(DecisionPresenterScreenModule())
    }

    private lateinit var component: DecisionScreenComponent

    private val applicationComponent: DecisionApplicationComponent
        get() {
            val application = application
            if (application !is DecisionApplicationComponentProvider) {
                throw IllegalStateException("The application must implement ${DecisionApplicationComponentProvider::class}")
            }

            return application.decisionApplicationComponent
        }
}