package moe.christina.decision.objects_list.di

import dagger.Binds
import dagger.Module
import moe.christina.decision.objects_list.ObjectsListFragment
import moe.christina.decision.objects_list.ObjectsListPresenter
import moe.christina.decision.objects_list.ObjectsListScreen
import moe.christina.mvp.presenter.Presenter

@Module
abstract class ObjectsListFragmentPresenterModule {
    @Binds
    abstract fun provideObjectsListScreen(presenter: ObjectsListFragment): ObjectsListScreen

    @Binds
    abstract fun provideObjectsListScreenPresenter(presenter: ObjectsListPresenter): Presenter<@JvmWildcard ObjectsListScreen>
}