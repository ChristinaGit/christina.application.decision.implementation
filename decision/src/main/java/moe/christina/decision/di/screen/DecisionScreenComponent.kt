package moe.christina.decision.di.screen

import dagger.Subcomponent
import moe.christina.decision.screen.activity.DecisionsViewerActivity
import moe.christina.decision.di.screen.module.DecisionPresenterScreenModule
import moe.christina.mvp.di.scope.ScreenScope

@Subcomponent(modules = arrayOf(DecisionPresenterScreenModule::class))
@ScreenScope
interface DecisionScreenComponent {
    fun inject(target: DecisionsViewerActivity)
}

