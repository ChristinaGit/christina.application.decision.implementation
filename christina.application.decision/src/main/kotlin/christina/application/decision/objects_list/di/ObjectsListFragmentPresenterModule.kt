package christina.application.decision.objects_list.di

import dagger.Binds
import dagger.Module
import christina.application.decision.objects_list.ObjectsListFragment
import christina.application.decision.objects_list.ObjectsListPresenter
import christina.application.decision.objects_list.ObjectsListScreen
import christina.library.android.architecture.mvp.di.qualifier.ScopeName
import christina.library.android.architecture.mvp.di.scope.FragmentScope
import christina.library.android.architecture.mvp.presenter.ScreenObserver
import javax.inject.Named

@FragmentScope
@Module
interface ObjectsListFragmentPresenterModule {
    @Binds
    fun provideObjectsListScreen(presenter: ObjectsListFragment): ObjectsListScreen

    @FragmentScope
    @Named(ScopeName.FRAGMENT)
    @Binds
    fun provideObjectsListScreenObserver(presenter: ObjectsListPresenter): ScreenObserver
}