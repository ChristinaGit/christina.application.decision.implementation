package christina.application.decision.decision

import christina.application.decision.decision.di.DaggerDecisionApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class DecisionApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<DecisionApplication> =
        DaggerDecisionApplicationComponent
            .builder()
            .create(this@DecisionApplication)
}