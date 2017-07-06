package moe.christina.decision.presenter

import android.support.annotation.CallSuper
import android.util.Log
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import moe.christina.common.android.AndroidConstant
import moe.christina.common.core.Event
import moe.christina.common.core.RxSchedulers
import moe.christina.decision.model.data.Decision
import moe.christina.decision.screen.DecisionsListScreen
import moe.christina.mvp.core.utility.displayLoadDataTransformer
import moe.christina.mvp.core.utility.displayRefreshDataTransformer
import java.util.Random
import java.util.concurrent.TimeUnit

class DecisionsListPresenter : BaseDecisionPresenter<DecisionsListScreen>() {
    companion object {
        @JvmStatic
        private val LOG_TAG = AndroidConstant.logTag<DecisionsListPresenter>()
    }

    @CallSuper
    override fun onBindScreen(screen: DecisionsListScreen) {
        super.onBindScreen(screen)

        loadDataEventObserver = screen.onLoadData.subscribe(this::loadDecisionsHandler)
        refreshDataEventObserver = screen.onRefreshData.subscribe(this::refreshDecisionsHandler)
    }

    @CallSuper
    override fun onUnbindScreen(screen: DecisionsListScreen) {
        super.onUnbindScreen(screen)

        loadDataEventObserver?.dispose()
        loadDataEventObserver = null

        refreshDataEventObserver?.dispose()
        refreshDataEventObserver = null
    }

    private val random = Random(2)

    private fun getDecisionsLoader(): Observable<List<Decision>> = Observable
        .range(random.nextInt(100), 20)
        .delay(2, TimeUnit.SECONDS)
        .map { Decision(it.toLong(), "Decision #$it", null) }
        .toList()
        .toObservable()
        .doOnNext({
            //                if (random.nextInt(100) > 65) {
            //                    throw IOException("Error test.")
            //                }
        })
        .subscribeOn(RxSchedulers.computation())
        .observeOn(RxSchedulers.main())

    private fun loadDecisionsHandler(event: Event) {
        loadDataObserver?.dispose()
        screen?.apply {
            loadDataObserver = getDecisionsLoader()
                .compose(displayLoadDataTransformer())
                .subscribe({}, { Log.w(LOG_TAG, "Failed to load decisions", it) })
        }
    }

    private fun refreshDecisionsHandler(event: Event) {
        loadDataObserver?.dispose()
        screen?.apply {
            loadDataObserver = getDecisionsLoader()
                .compose(displayRefreshDataTransformer())
                .subscribe({}, { Log.w(LOG_TAG, "Failed to refresh decisions", it) })
        }
    }

    private var loadDataObserver: Disposable? = null

    private var loadDataEventObserver: Disposable? = null
    private var refreshDataEventObserver: Disposable? = null
}
