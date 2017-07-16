package moe.christina.decision.screen.fragment

import android.support.annotation.CallSuper
import dagger.android.support.AndroidSupportInjection
import moe.christina.mvp.android.support.ScreenFragment

abstract class BaserDecisionFragment : ScreenFragment() {
    @CallSuper
    override fun onInjectMembers() {
        super.onInjectMembers()

        AndroidSupportInjection.inject(this)
    }
}