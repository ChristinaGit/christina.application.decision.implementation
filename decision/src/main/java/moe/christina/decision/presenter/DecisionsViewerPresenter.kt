package moe.christina.decision.presenter

import android.support.annotation.CallSuper
import moe.christina.decision.screen.DecisionsViewerScreen
import moe.christina.mvp.presenter.BasePresenter

class DecisionsViewerPresenter : BasePresenter<DecisionsViewerScreen>() {
    var counter: Int = 0

    @CallSuper
    override fun onScreenAppear(screen: DecisionsViewerScreen) {
        super.onScreenAppear(screen)

        screen.displayMessage("Hi there! ${counter++}")
    }
}