package christina.application.decision.decisions_list.domain.use_case

import christina.application.decision.decision.persistence.DecisionStorage
import christina.application.decision.decision.persistence.core.decision.DecisionEntity
import christina.application.decision.decisions_list.domain.model.Decision
import christina.library.android.architecture.mvp.di.qualifier.ScopeName
import christina.library.android.architecture.mvp.interactor.ActionInteractor
import christina.library.android.common.rx.RxManager
import christina.library.android.common.rx.RxSchedulers
import christina.library.android.common.rx.autoManage
import christina.library.mapper.core.Mapper
import christina.library.mapper.core.mapMany
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Named

class GetDecisionsInteractor
@Inject
constructor(
    private val mapper: Mapper,
    @Named(ScopeName.FRAGMENT)
    private val rxManager: RxManager,
    private val storage: DecisionStorage
) : ActionInteractor<Observable<List<Decision>>> {
    override fun execute(argument: Unit): Observable<List<Decision>> {
        return storage
            .decisions
            .queryAll()
            .autoManage(rxManager)
            .subscribeOn(RxSchedulers.io())
            .map { return@map mapper.mapMany<DecisionEntity, Decision>(it) }
    }
}