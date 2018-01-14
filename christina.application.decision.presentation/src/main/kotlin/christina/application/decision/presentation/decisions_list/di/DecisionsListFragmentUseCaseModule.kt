package christina.application.decision.presentation.decisions_list.di

import christina.application.decision.domain.use_case.decision.get_list.GetDecisionsListInteractor
import christina.application.decision.persistence.DecisionStorage
import christina.application.decision.presentation.core.qualifier.ScopeName
import christina.application.decision.presentation.core.scope.FragmentScope
import christina.library.android.common.rx.RxManager
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