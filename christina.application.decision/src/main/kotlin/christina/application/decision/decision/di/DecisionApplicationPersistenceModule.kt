package christina.application.decision.decision.di

import christina.application.decision.decision.persistence.DecisionStorage
import christina.application.decision.decision.persistence.core.decision.DecisionEntity
import christina.application.decision.decision.persistence.core.decision.DecisionEntityData
import christina.application.decision.decision.persistence.core.decision.DecisionStore
import christina.application.decision.decision.persistence.memory.DecisionMemoryStorage
import christina.application.decision.decision.persistence.memory.DecisionMemoryStore
import christina.library.android.architecture.mvp.di.scope.ApplicationScope
import christina.library.mapper.basic.BasicMapper
import christina.library.mapper.core.Mapper
import christina.library.mapper.delegate.host.DelegateMappingHost
import christina.library.mapper.delegate.host.addMapping
import christina.library.mapper.delegate.host.addReceiverMapping
import dagger.Module
import dagger.Provides

@ApplicationScope
@Module
class DecisionApplicationPersistenceModule {
    @ApplicationScope
    @Provides
    fun provideDecisionStorage(
        decisionStore: DecisionStore
    ): DecisionStorage = DecisionMemoryStorage(
        decisionStore
    )

    @ApplicationScope
    @Provides
    fun provideDecisionStore(mapper: Mapper): DecisionStore = DecisionMemoryStore(mapper)

    @ApplicationScope
    @Provides
    fun provideMapper(): Mapper = BasicMapper(
        DelegateMappingHost().apply {
            addMapping<DecisionEntity, DecisionEntity> {
                DecisionEntity(it.id, it.name)
            }
            addReceiverMapping<DecisionEntityData, DecisionEntity> { source, destination ->
//                destination.name =
            }
        }
    )
}