package christina.application.decision.implementation.objects_list.di

import christina.application.decision.implementation.core.scope.FragmentScope
import christina.application.decision.implementation.objects_list.ObjectsListFragment
import christina.application.decision.presentation.objects_list.ObjectsListScreen
import dagger.Binds
import dagger.Module

@FragmentScope
@Module
interface ObjectsListFragmentPresenterBindsModule {
    @Binds
    fun provideObjectsListScreen(presenter: ObjectsListFragment): ObjectsListScreen
}