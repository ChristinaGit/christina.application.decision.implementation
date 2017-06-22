package moe.christina.decision.presenter

import android.support.annotation.CallSuper
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import moe.christina.common.core.RxSchedulers
import moe.christina.decision.model.Decision
import moe.christina.decision.screen.DecisionsListScreen
import moe.christina.mvp.presenter.BasePresenter
import moe.christina.mvp.screen.behavior.ViewItemsEvent
import java.util.concurrent.TimeUnit

class DecisionsListPresenter : BasePresenter<DecisionsListScreen>() {
    @CallSuper
    override fun onBindScreen(screen: DecisionsListScreen) {
        super.onBindScreen(screen)

        viewItemsObserver = screen.onViewItems.subscribe(this::viewItemsHandler)
    }

    @CallSuper
    override fun onUnbindScreen(screen: DecisionsListScreen) {
        super.onUnbindScreen(screen)

        viewItemsObserver?.dispose()
    }

    private fun viewItemsHandler(event: ViewItemsEvent) {
        screen?.displayItemsLoading()

        Observable
                .range(0, 20)
                .delay(3, TimeUnit.SECONDS)
                .map { Decision("Decision #$it") }
                .toList()
                .subscribeOn(RxSchedulers.computation())
                .observeOn(RxSchedulers.main())
                .subscribe(
                        { screen?.displayItems(it) },
                        { screen?.displayItemsLoadError() })
    }

    private var viewItemsObserver: Disposable? = null
}
