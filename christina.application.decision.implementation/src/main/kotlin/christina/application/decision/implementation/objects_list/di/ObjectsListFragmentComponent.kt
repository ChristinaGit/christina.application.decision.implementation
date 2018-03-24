package christina.application.decision.implementation.objects_list.di

import christina.application.decision.implementation.core.scope.FragmentScope
import christina.application.decision.implementation.objects_list.ObjectsListFragment
import dagger.Subcomponent
import dagger.android.AndroidInjector

@FragmentScope
@Subcomponent(modules = [
    ObjectsListFragmentPresenterModule::class,
    ObjectsListFragmentPresenterBindsModule::class
])
interface ObjectsListFragmentComponent : AndroidInjector<ObjectsListFragment> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<ObjectsListFragment>() {
        abstract override fun build(): ObjectsListFragmentComponent
    }
}