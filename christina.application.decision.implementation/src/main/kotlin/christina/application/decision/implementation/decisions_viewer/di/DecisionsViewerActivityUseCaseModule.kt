package christina.application.decision.implementation.decisions_viewer.di

import christina.application.decision.domain.use_case.decision.create.CreateDecisionInteractor
import christina.application.decision.persistence.core.DecisionStorage
import christina.application.decision.implementation.core.qualifier.ScopeName
import christina.application.decision.implementation.core.scope.ActivityScope
import christina.library.application.rx.RxManager
import christina.library.mapper.core.Mapper
import dagger.Module
import dagger.Provides
import javax.inject.Named

@ActivityScope
@Module
class DecisionsViewerActivityUseCaseModule {
    @ActivityScope
    @Provides
    fun provideCreateDecisionInteractor(
        mapper: Mapper,
        @Named(ScopeName.ACTIVITY)
        rxManager: RxManager,
        storage: DecisionStorage
    ): CreateDecisionInteractor = CreateDecisionInteractor(
        mapper,
        rxManager,
        storage
    )
}