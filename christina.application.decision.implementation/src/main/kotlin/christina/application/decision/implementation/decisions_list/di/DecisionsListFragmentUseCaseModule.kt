package christina.application.decision.implementation.decisions_list.di

import christina.application.decision.domain.use_case.decision.get_list.GetDecisionsListInteractor
import christina.application.decision.persistence.core.DecisionStorage
import christina.application.decision.implementation.core.qualifier.ScopeName
import christina.application.decision.implementation.core.scope.FragmentScope
import christina.library.application.rx.RxManager
import christina.library.mapper.core.Mapper
import dagger.Module
import dagger.Provides
import javax.inject.Named

@FragmentScope
@Module
class DecisionsListFragmentUseCaseModule {
    @FragmentScope
    @Provides
    fun provideGetDecisionsListInteractor(
        mapper: Mapper,
        @Named(ScopeName.FRAGMENT)
        rxManager: RxManager,
        storage: DecisionStorage
    ): GetDecisionsListInteractor = GetDecisionsListInteractor(
        mapper,
        rxManager,
        storage
    )
}