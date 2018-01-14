package christina.application.decision.presentation.decisions_viewer.di

import christina.application.decision.domain.use_case.decision.create.CreateDecisionInteractor
import christina.application.decision.persistence.DecisionStorage
import christina.application.decision.presentation.core.qualifier.ScopeName
import christina.application.decision.presentation.core.scope.ActivityScope
import christina.library.android.common.rx.RxManager
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