package christina.application.decision.decision.persistence.storage.store

import christina.application.decision.decision.persistence.entity.DecisionEntity
import christina.application.decision.decision.persistence.entity_data.DecisionEntityData
import christina.application.decision.decision.persistence.storage.DecisionSelector
import christina.application.decision.decision.persistence.storage.ObservableStoreQuery
import christina.common.data.presistence.storage.core.store.Store

interface DecisionStore : Store<DecisionEntity, DecisionEntityData, DecisionSelector> {
    override fun query(selector: DecisionSelector): ObservableStoreQuery<DecisionEntity>

    override fun queryAll(): ObservableStoreQuery<DecisionEntity>
}