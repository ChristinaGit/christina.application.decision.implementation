package christina.application.decision.decisions_viewer

import io.reactivex.disposables.Disposable
import christina.library.android.common.rx.RxSchedulers
import christina.common.event.core.eventListener
import christina.common.event.core.minusAssign
import christina.common.event.core.plusAssign
import christina.application.decision.core.BaseDecisionPresenter
import christina.application.decision.decisions_viewer.domain.use_case.CreateDecisionInteractor
import christina.library.android.architecture.mvp.di.scope.ActivityScope
import christina.library.android.architecture.mvp.interactor.invoke
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.warn
import javax.inject.Inject

@ActivityScope
class DecisionsViewerPresenter
@Inject
constructor(
    screen: DecisionsViewerScreen,
    private val createDecisionInteractor: CreateDecisionInteractor
) : BaseDecisionPresenter<DecisionsViewerScreen>(screen),
    AnkoLogger {

    override fun onSubscribe() {
        super.onSubscribe()

        screen.onRequestCreateDecision += onRequestCreateDecision
    }

    override fun onUnsubscribe() {
        super.onUnsubscribe()

        screen.onRequestCreateDecision -= onRequestCreateDecision

        onCreateDecisionObserver = null
    }

    private val onRequestCreateDecision = eventListener {
        onCreateDecisionObserver = createDecisionInteractor()
            .observeOn(RxSchedulers.main())
            .displayContent(screen.createdDecisionScreenView)
            .subscribe({}, { warn("Fail to create entity.", it) })
    }

    private var onCreateDecisionObserver: Disposable? = null
        set(value) {
            field?.dispose()

            field = value
        }
}