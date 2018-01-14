package christina.application.decision.presentation.objects_list.di

import christina.application.decision.presentation.core.scope.FragmentScope
import christina.application.decision.presentation.objects_list.ObjectsListFragment
import christina.application.decision.presentation.objects_list.ObjectsListPresenter
import christina.application.decision.presentation.objects_list.domain.usecase.GetObjectsInteractor
import dagger.Subcomponent
import dagger.android.AndroidInjector

@FragmentScope
@Subcomponent(modules = [ObjectsListFragmentPresenterModule::class])
interface ObjectsListFragmentComponent : AndroidInjector<ObjectsListFragment> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<ObjectsListFragment>() {
        abstract override fun build(): ObjectsListFragmentComponent
    }

    fun getObjectsListPresenter(): ObjectsListPresenter

    fun getGetObjectsInteractor(): GetObjectsInteractor
}