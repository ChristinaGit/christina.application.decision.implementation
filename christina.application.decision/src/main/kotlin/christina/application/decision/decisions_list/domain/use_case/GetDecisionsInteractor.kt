package christina.application.decision.decisions_list.domain.use_case

import io.reactivex.Observable
import christina.library.android.common.rx.RxManager
import christina.library.android.common.rx.RxSchedulers
import christina.application.decision.decision.persistence.storage.DecisionStorage
import christina.application.decision.decisions_list.domain.model.Decision
import christina.library.android.architecture.mvp.di.qualifier.ScopeName
import christina.library.android.architecture.mvp.interactor.ActionInteractor
import javax.inject.Inject
import javax.inject.Named

class GetDecisionsInteractor
@Inject
constructor(
    @Named(ScopeName.FRAGMENT)
    private val rxManager: RxManager,
    private val database: DecisionStorage
) : ActionInteractor<Observable<List<Decision>>> {
    override fun execute(argument: Unit): Observable<List<Decision>> {
        return database.decisions.loadAll()
            .map { it.map { Decision(it.id, it.name) }.toList() }
            .toObservable()
            .autoManage(rxManager)
            .subscribeOn(RxSchedulers.io())
    }
}