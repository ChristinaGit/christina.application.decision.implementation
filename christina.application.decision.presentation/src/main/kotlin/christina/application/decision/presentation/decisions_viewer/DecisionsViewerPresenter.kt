package christina.application.decision.presentation.decisions_viewer

import christina.application.decision.domain.use_case.decision.create.CreateDecisionInteractor
import christina.application.decision.presentation.core.BaseDecisionPresenter
import christina.application.decision.presentation.core.scope.ActivityScope
import christina.common.event.core.eventListener
import christina.common.event.core.minusAssign
import christina.common.event.core.plusAssign
import christina.library.android.architecture.mvp.interactor.invoke
import christina.library.android.architecture.mvp.screen_view.content.displayContent
import christina.library.android.common.rx.RxSchedulers
import io.reactivex.disposables.Disposable
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
            .map { it.decision.name }
            .displayContent(screen.createdDecisionScreenView)
            .subscribe({}, { warn("Fail to create entity.", it) })
    }

    private var onCreateDecisionObserver: Disposable? = null
        set(value) {
            field?.dispose()

            field = value
        }
}