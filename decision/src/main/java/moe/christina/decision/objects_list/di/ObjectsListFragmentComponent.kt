package moe.christina.decision.objects_list.di

import dagger.Subcomponent
import dagger.android.AndroidInjector
import moe.christina.decision.objects_list.ObjectsListFragment
import moe.christina.decision.objects_list.ObjectsListPresenter
import moe.christina.decision.objects_list.domain.usecase.GetObjectsInteractor

@Subcomponent(modules = arrayOf(ObjectsListFragmentPresenterModule::class))
interface ObjectsListFragmentComponent : AndroidInjector<ObjectsListFragment> {
    @Subcomponent.Builder
    abstract class Builder : AndroidInjector.Builder<ObjectsListFragment>() {
        abstract override fun build(): ObjectsListFragmentComponent
    }

    fun getObjectsListPresenter(): ObjectsListPresenter

    fun getGetObjectsInteractor(): GetObjectsInteractor
}