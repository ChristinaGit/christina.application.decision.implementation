package christina.application.decision.decision.di

import dagger.Module
import dagger.Provides
import christina.application.decision.decision.persistence.storage.DecisionStorage
import christina.application.decision.decision.persistence.storage.memory.DecisionMemoryStorage
import christina.library.android.architecture.mvp.di.scope.ApplicationScope

@ApplicationScope
@Module
class DecisionApplicationPersistenceModule {
    @ApplicationScope
    @Provides
    fun provideDecisionDatabase(): DecisionStorage = DecisionMemoryStorage()
}