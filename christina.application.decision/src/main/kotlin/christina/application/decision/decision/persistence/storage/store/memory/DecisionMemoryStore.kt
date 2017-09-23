package christina.application.decision.decision.persistence.storage.store.memory

import christina.application.decision.decision.persistence.entity.DecisionEntity
import christina.application.decision.decision.persistence.entity_data.DecisionEntityData
import christina.application.decision.decision.persistence.storage.DecisionSelector
import christina.application.decision.decision.persistence.storage.ObservableStoreQuery
import christina.application.decision.decision.persistence.storage.store.DecisionStore
import christina.common.data.presistence.storage.memory.store.MemoryStore

class DecisionMemoryStore :
    MemoryStore<DecisionEntity, DecisionEntityData, DecisionSelector>(),
    DecisionStore {
    override fun createEntity(): DecisionEntity = DecisionEntity(entities.maxBy(DecisionEntity::id)?.id ?: 1L)

    override fun query(selector: DecisionSelector): ObservableStoreQuery<DecisionEntity> =
        super.query(selector) as ObservableStoreQuery<DecisionEntity>

    override fun queryAll(): ObservableStoreQuery<DecisionEntity> =
        super.queryAll() as ObservableStoreQuery<DecisionEntity>

    override fun applySelector(entity: DecisionEntity, selector: DecisionSelector): Boolean = true

    override fun updateEntity(entity: DecisionEntity, data: DecisionEntityData) {

    }

    override fun transformToQuery(entities: Iterable<DecisionEntity>): ObservableStoreQuery<DecisionEntity> {
        TODO("not implemented")
    }
}