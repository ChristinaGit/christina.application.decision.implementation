package christina.application.decision.decisions_viewer.domain.use_case

import christina.application.decision.decision.persistence.DecisionStorage
import christina.application.decision.decision.persistence.core.decision.DecisionEntityData
import christina.application.decision.decisions_viewer.domain.model.CreatedDecision
import christina.library.android.architecture.mvp.di.qualifier.ScopeName
import christina.library.android.architecture.mvp.interactor.ActionInteractor
import christina.library.android.common.rx.RxManager
import christina.library.android.common.rx.RxSchedulers
import christina.library.android.common.rx.autoManage
import io.reactivex.Observable
import java.util.UUID
import javax.inject.Inject
import javax.inject.Named

class CreateDecisionInteractor
@Inject
constructor(
    @Named(ScopeName.ACTIVITY)
    private val rxManager: RxManager,
    private val storage: DecisionStorage
) : ActionInteractor<Observable<CreatedDecision>> {
    override fun execute(argument: Unit): Observable<CreatedDecision> {
        val name = UUID.randomUUID().toString()
        storage.decisions.create(DecisionEntityData().apply { this.name.set(name) })
        return Observable
            .just(CreatedDecision(name))
            .autoManage(rxManager)
            .subscribeOn(RxSchedulers.io())
    }
}
