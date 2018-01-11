package christina.application.decision.decision.persistence.memory

import christina.application.decision.decision.persistence.core.decision.DecisionEntity
import christina.application.decision.decision.persistence.core.decision.DecisionEntityData
import christina.application.decision.decision.persistence.core.decision.DecisionSelector
import christina.application.decision.decision.persistence.core.decision.DecisionStore
import christina.common.data.presistence.storage.memory.store.MemoryStore
import christina.common.indexer.Indexers
import christina.common.indexer.core.Indexer
import christina.library.mapper.core.Mapper
import christina.library.mapper.core.map
import io.reactivex.Observable

class DecisionMemoryStore(
    private val mapper: Mapper
) : MemoryStore<DecisionEntity, DecisionEntityData, DecisionSelector, Observable<List<DecisionEntity>>>(),
    DecisionStore {
    private val idIndexer: Indexer<Long> = Indexers.long()

    override fun transformToQuery(entities: Iterable<DecisionEntity>): Observable<List<DecisionEntity>> =
        Observable.just(entities.toList())

    override fun createEntity(): DecisionEntity = DecisionEntity(idIndexer.newIndex())

    override fun applySelector(entity: DecisionEntity, selector: DecisionSelector): Boolean = true

    override fun updateEntity(entity: DecisionEntity, data: DecisionEntityData) {
        mapper.map(data, entity)
    }

    override fun extractEntity(entity: DecisionEntity): DecisionEntity = mapper.map(entity)
}