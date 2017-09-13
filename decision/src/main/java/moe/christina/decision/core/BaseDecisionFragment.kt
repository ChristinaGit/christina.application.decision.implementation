package moe.christina.decision.core

import android.support.annotation.CallSuper
import dagger.android.support.AndroidSupportInjection
import moe.christina.common.android.support.ObservableFragment

abstract class BaseDecisionFragment :
    ObservableFragment() {
    @CallSuper
    override fun onInjectMembers() {
        super.onInjectMembers()

        AndroidSupportInjection.inject(this)
    }
}