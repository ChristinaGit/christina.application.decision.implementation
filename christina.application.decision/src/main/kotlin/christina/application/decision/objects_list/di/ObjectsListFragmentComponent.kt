package christina.application.decision.objects_list.di

import dagger.Subcomponent
import dagger.android.AndroidInjector
import christina.application.decision.objects_list.ObjectsListFragment
import christina.application.decision.objects_list.ObjectsListPresenter
import christina.application.decision.objects_list.domain.usecase.GetObjectsInteractor
import christina.library.android.architecture.mvp.di.scope.FragmentScope

@FragmentScope
@Subcomponent(modules = arrayOf(ObjectsListFragmentPresenterModule::class))
interface ObjectsListFragmentComponent : AndroidInjector<ObjectsListFragment> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<ObjectsListFragment>() {
        abstract override fun build(): ObjectsListFragmentComponent
    }

    fun getObjectsListPresenter(): ObjectsListPresenter

    fun getGetObjectsInteractor(): GetObjectsInteractor
}