package moe.christina.decision.di.subscreen

import dagger.Subcomponent
import moe.christina.decision.di.subscreen.module.DecisionPresenterSubscreenModule
import moe.christina.decision.screen.fragment.DecisionsListFragment
import moe.christina.mvp.di.scope.SubscreenScope

@Subcomponent(modules = arrayOf(DecisionPresenterSubscreenModule::class))
@SubscreenScope
interface DecisionSubscreenComponent {
    fun inject(target: DecisionsListFragment)
}