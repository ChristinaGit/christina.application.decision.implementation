package christina.application.decision.persistence.decision.store

import christina.application.decision.persistence.decision.DecisionEntity
import christina.application.decision.persistence.decision.DecisionEntityData
import christina.application.decision.persistence.decision.DecisionSelector
import christina.common.data.presistence.storage.core.store.Store
import io.reactivex.Observable

interface DecisionStore : Store<
    DecisionEntity,
    DecisionEntityData,
    DecisionSelector,
    Observable<List<DecisionEntity>>>