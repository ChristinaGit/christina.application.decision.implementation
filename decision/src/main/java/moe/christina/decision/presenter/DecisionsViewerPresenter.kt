package moe.christina.decision.presenter

import android.support.annotation.CallSuper
import io.reactivex.disposables.Disposable
import moe.christina.common.core.Event
import moe.christina.decision.screen.DecisionsViewerScreen
import moe.christina.mvp.presenter.BasePresenter

class DecisionsViewerPresenter : BasePresenter<DecisionsViewerScreen>() {
    @CallSuper
    override fun onScreenAppear(screen: DecisionsViewerScreen) {
        super.onScreenAppear(screen)

    }

    override fun onBindScreen(screen: DecisionsViewerScreen) {
        super.onBindScreen(screen)

        createDecisionObserver = screen.onCreateDecision.subscribe(this::createDecisionHandler)
    }

    override fun onUnbindScreen(screen: DecisionsViewerScreen) {
        super.onUnbindScreen(screen)

        createDecisionObserver?.dispose()
        createDecisionObserver = null
    }

    private fun createDecisionHandler(event: Event) {
    }

    private var createDecisionObserver: Disposable? = null
}