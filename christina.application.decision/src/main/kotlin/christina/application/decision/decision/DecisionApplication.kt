package christina.application.decision.decision

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication
import moe.christina.decision.decision.di.DaggerDecisionApplicationComponent

class DecisionApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<DecisionApplication> =
        DaggerDecisionApplicationComponent
            .builder()
            .create(this@DecisionApplication)
}