package christina.application.decision.decision.persistence.storage

import christina.common.data.presistence.storage.core.store.query.StoreQuery
import io.reactivex.Observable

interface ObservableStoreQuery<TEntity> : StoreQuery<TEntity> {
    fun asObservable(): Observable<TEntity>
}