package christina.application.decision.presentation.decisions_viewer

import christina.application.decision.domain.use_case.decision.create.CreateDecisionInteractor
import christina.application.decision.presentation.core.BaseDecisionPresenter
import christina.application.decision.presentation.core.scope.ActivityScope
import christina.common.rx.switchOnNext
import christina.library.android.architecture.mvp.interactor.invoke
import christina.library.android.architecture.mvp.screen_view.content.transformer.displayContent
import christina.library.android.common.rx.RxSchedulers
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import org.jetbrains.anko.AnkoLogger
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

        onCreateDecisionSubscription =
            screen
                .onRequestCreateDecision
                .map {
                    createDecisionInteractor()
                        .observeOn(RxSchedulers.main())
                        .map { it.decision.name }
                        .displayContent(screen.createdDecisionScreenView)
                        .onErrorResumeNext(Observable.empty())
                }
                .switchOnNext()
                .subscribe()
    }

    override fun onUnsubscribe() {
        super.onUnsubscribe()

        onCreateDecisionSubscription = null
    }

    private var onCreateDecisionSubscription: Disposable? = null
        set(value) {
            field?.dispose()

            field = value
        }
}