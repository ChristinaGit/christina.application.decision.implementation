package christina.application.decision.presentation.decision.di

import christina.application.decision.domain.use_case.decision.addDecisionDomainMappings
import christina.application.decision.persistence.DecisionMemoryStorage
import christina.application.decision.persistence.DecisionStorage
import christina.application.decision.persistence.decision.DecisionEntity
import christina.application.decision.persistence.decision.DecisionEntityData
import christina.application.decision.persistence.decision.store.DecisionMemoryStore
import christina.application.decision.persistence.decision.store.DecisionStore
import christina.application.decision.presentation.core.scope.ApplicationScope
import christina.common.data.presistence.storage.core.entity_data.EntityDataPropertyState
import christina.library.mapper.basic.basicMapper
import christina.library.mapper.core.Mapper
import christina.library.mapper.core.map
import christina.library.mapper.delegate.host.DelegateMappingHost
import christina.library.mapper.delegate.host.addMapping
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
    fun provideMapper(): Mapper {
        val mappingHost = DelegateMappingHost()
        val mapper = mappingHost.basicMapper()

        mappingHost.apply {
            addDecisionDomainMappings(mapper)

            addMapping<DecisionEntity, DecisionEntity> {
                DecisionEntity().apply {
                    id = it.id
                    name = it.name
                }
            }
            addMapping<DecisionEntityData, DecisionEntity>(
                { DecisionEntity().apply { mapper.map(this, it) } },
                { source, destination ->
                    destination.apply {
                        if (source.name.state == EntityDataPropertyState.Changed) {
                            name = source.name.get()
                        }
                    }
                })
        }

        return mapper
    }
}