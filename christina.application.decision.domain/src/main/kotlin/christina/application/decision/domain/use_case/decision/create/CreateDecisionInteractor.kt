package christina.application.decision.domain.use_case.decision.create

import christina.application.decision.persistence.DecisionStorage
import christina.application.decision.persistence.decision.DecisionEntityData
import christina.library.android.architecture.mvp.interactor.ActionInteractor
import christina.library.android.common.rx.RxManager
import christina.library.android.common.rx.RxSchedulers
import christina.library.android.common.rx.autoManage
import christina.library.mapper.core.Mapper
import christina.library.mapper.core.map
import io.reactivex.Observable
import java.util.UUID

class CreateDecisionInteractor
constructor(
    private val mapper: Mapper,
    private val rxManager: RxManager,
    private val storage: DecisionStorage
) : ActionInteractor<Observable<CreateDecisionResult>> {
    override fun execute(argument: Unit): Observable<CreateDecisionResult> =
        Observable
            .just(storage.decisions.create(DecisionEntityData().apply { name.set(UUID.randomUUID().toString()) }))
            .subscribeOn(RxSchedulers.io())
            .observeOn(RxSchedulers.computation())
            .map { CreateDecisionResult(mapper.map(it)) }
            .autoManage(rxManager)
            .observeOn(RxSchedulers.main())
}
