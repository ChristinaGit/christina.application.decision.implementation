package moe.christina.decision.decisions_viewer

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import moe.christina.android.templates.behavior.task.displayTaskOnScreen
import moe.christina.android.templates.behavior.task.onRequestPerformTask
import moe.christina.common.core.RxSchedulers
import moe.christina.common.core.event.Event
import moe.christina.decision.core.BaseDecisionPresenter
import moe.christina.decision.decisions_viewer.domain.usecase.CreateDecisionInteractor
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.warn
import javax.inject.Inject

class DecisionsViewerPresenter
@Inject
constructor(
    screen: DecisionsViewerScreen,
    private val createDecisionInteractor: CreateDecisionInteractor
) : BaseDecisionPresenter<DecisionsViewerScreen>(screen),
    AnkoLogger {

    override fun onBindScreen() {
        super.onBindScreen()

        screenObservers.add(screen.onRequestPerformTask.subscribe(this::onHandleCreateDecision))
    }

    override fun onUnbindScreen() {
        super.onUnbindScreen()

        onCreateDecisionObserver = null

        screenObservers.dispose()
    }

    private fun onHandleCreateDecision(event: Event) {
        onCreateDecisionObserver = createDecisionInteractor()
            .observeOn(RxSchedulers.main())
            .displayTaskOnScreen(screen, { Unit }, { it.message ?: it.toString() })
            .subscribe({}, { warn("Fail to create entity.", it) })
    }

    private val screenObservers: CompositeDisposable = CompositeDisposable()

    private var onCreateDecisionObserver: Disposable? = null
        set(value) {
            field?.dispose()

            field = value
        }
}