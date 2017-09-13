package moe.christina.decision.decisions_viewer.domain.usecase

import io.reactivex.Observable
import moe.christina.common.core.RxSchedulers
import moe.christina.decision.decisions_viewer.domain.model.CreatedDecision
import moe.christina.mvp.interactor.TaskInteractor
import javax.inject.Inject

class CreateDecisionInteractor
@Inject
constructor() : TaskInteractor<CreatedDecision> {
    override operator fun invoke(): Observable<CreatedDecision> =
        Observable
            .just(CreatedDecision())
            .subscribeOn(RxSchedulers.io())
}
