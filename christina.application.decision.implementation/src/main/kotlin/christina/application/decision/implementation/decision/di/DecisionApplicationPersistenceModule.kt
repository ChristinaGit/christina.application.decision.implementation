package christina.application.decision.implementation.decision.di

import christina.application.decision.domain.use_case.decision.addDecisionDomainMappings
import christina.application.decision.implementation.core.scope.ApplicationScope
import christina.application.decision.persistence.core.DecisionStorage
import christina.application.decision.persistence.core.addDecisionPersistenceMappings
import christina.application.decision.persistence.core.decision.DecisionStore
import christina.application.decision.persistence.memory.DecisionMemoryStorage
import christina.application.decision.persistence.memory.DecisionMemoryStore
import christina.application.decision.presentation.decisions_viewer.addDecisionViewerMappings
import christina.library.mapper.basic.basicMapper
import christina.library.mapper.core.Mapper
import christina.library.mapper.delegate.host.DelegateMappingHost
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
            addDecisionPersistenceMappings(mapper)
            addDecisionDomainMappings(mapper)

            addDecisionViewerMappings(mapper)
        }

        return mapper
    }
}