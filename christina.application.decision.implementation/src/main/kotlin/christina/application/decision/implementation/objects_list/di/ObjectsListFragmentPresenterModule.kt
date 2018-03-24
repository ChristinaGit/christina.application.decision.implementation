package christina.application.decision.implementation.objects_list.di

import christina.application.decision.implementation.core.qualifier.ScopeName
import christina.application.decision.implementation.core.scope.FragmentScope
import christina.application.decision.presentation.objects_list.ObjectsListPresenter
import christina.application.decision.presentation.objects_list.ObjectsListScreen
import christina.library.android.architecture.mvp.presenter.ScreenObserver
import dagger.Module
import dagger.Provides
import javax.inject.Named

@FragmentScope
@Module
class ObjectsListFragmentPresenterModule {
    @FragmentScope
    @Named(ScopeName.FRAGMENT)
    @Provides
    fun provideObjectsViewerScreenObserver(
        screen: ObjectsListScreen
    ): ScreenObserver = ObjectsListPresenter(screen)
}