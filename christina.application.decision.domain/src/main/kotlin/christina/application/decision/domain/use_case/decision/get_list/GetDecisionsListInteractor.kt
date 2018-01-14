package christina.application.decision.domain.use_case.decision.get_list

import christina.application.decision.persistence.DecisionStorage
import christina.library.android.architecture.mvp.interactor.ActionInteractor
import christina.library.android.common.rx.RxManager
import christina.library.android.common.rx.RxSchedulers
import christina.library.android.common.rx.autoManage
import christina.library.mapper.core.Mapper
import christina.library.mapper.core.mapMany
import io.reactivex.Observable

class GetDecisionsListInteractor
constructor(
    private val mapper: Mapper,
    private val rxManager: RxManager,
    private val storage: DecisionStorage
) : ActionInteractor<Observable<GetDecisionsListResult>> {
    override fun execute(argument: Unit): Observable<GetDecisionsListResult> {
        return storage
            .decisions
            .queryAll()
            .autoManage(rxManager)
            .subscribeOn(RxSchedulers.io())
            .map { return@map GetDecisionsListResult(mapper.mapMany(it)) }
    }
}