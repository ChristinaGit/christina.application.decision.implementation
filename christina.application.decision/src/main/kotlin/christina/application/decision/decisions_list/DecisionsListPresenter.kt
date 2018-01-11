package christina.application.decision.decisions_list

import christina.application.decision.core.BaseDecisionPresenter
import christina.application.decision.decisions_list.domain.use_case.GetDecisionsInteractor
import christina.common.event.core.eventListener
import christina.common.event.core.minusAssign
import christina.common.event.core.plusAssign
import christina.library.android.architecture.mvp.interactor.invoke
import christina.library.android.architecture.mvp.screen_view.task.displayTask
import christina.library.android.common.rx.RxSchedulers
import io.reactivex.disposables.Disposable
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug
import org.jetbrains.anko.warn
import javax.inject.Inject

class DecisionsListPresenter
@Inject
constructor(
    screen: DecisionsListScreen,
    private val getDecisionsInteractor: GetDecisionsInteractor) :
    BaseDecisionPresenter<DecisionsListScreen>(screen),
    AnkoLogger {
    override fun onSubscribe() {
        super.onSubscribe()

        screen.onLoadDecisionsList += onLoadDecisionsList
    }

    override fun onUnsubscribe() {
        super.onUnsubscribe()

        screen.onLoadDecisionsList -= onLoadDecisionsList

        onLoadDecisionsObserver = null
    }

    private val onLoadDecisionsList = eventListener {
        onLoadDecisionsObserver = getDecisionsInteractor()
            .observeOn(RxSchedulers.main())
            .displayTask(screen.decisionsListScreenView, { it.message ?: it.toString() })
            .subscribe(
                { debug("onNext: ${it.size}") },
                { warn("Failed to get all decisions", it) },
                { debug("onComplete") })
    }

    private var onLoadDecisionsObserver: Disposable? = null
        set(value) {
            field?.dispose()

            field = value
        }
}