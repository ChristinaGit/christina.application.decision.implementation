package christina.application.decision.presentation.decisions_list

import christina.application.decision.domain.use_case.decision.get_list.GetDecisionsListInteractor
import christina.application.decision.presentation.core.BaseDecisionPresenter
import christina.common.event.core.eventListener
import christina.common.event.core.minusAssign
import christina.common.event.core.plusAssign
import christina.library.android.architecture.mvp.interactor.invoke
import christina.library.android.architecture.mvp.screen_view.task.displayTask
import christina.library.android.common.rx.RxSchedulers
import io.reactivex.disposables.Disposable
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.warn
import javax.inject.Inject

class DecisionsListPresenter
@Inject
constructor(
    screen: DecisionsListScreen,
    private val getDecisionsListInteractor: GetDecisionsListInteractor
) : BaseDecisionPresenter<DecisionsListScreen>(screen),
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
        onLoadDecisionsObserver = getDecisionsListInteractor()
            .observeOn(RxSchedulers.main())
            .map { it.decisions.map { it.name } }
            .displayTask(screen.decisionsListScreenView, { it.message ?: it.toString() })
            .subscribe(
                { info("onNext: ${it.size}") },
                { warn("Failed to get all decisions", it) },
                { info("onComplete") })
    }

    private var onLoadDecisionsObserver: Disposable? = null
        set(value) {
            field?.dispose()

            field = value
        }
}