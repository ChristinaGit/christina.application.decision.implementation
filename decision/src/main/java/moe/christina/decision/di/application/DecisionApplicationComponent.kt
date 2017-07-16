package moe.christina.decision.di.application

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import moe.christina.decision.DecisionApplication
import moe.christina.mvp.di.scope.ApplicationScope

@Component(modules = arrayOf(
    AndroidInjectionModule::class,
    DecisionActivityProviderModule::class,
    DecisionApplicationModule::class))
@ApplicationScope
interface DecisionApplicationComponent {
    fun inject(target: DecisionApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance fun application(application: Application): Builder
        fun build(): DecisionApplicationComponent
    }
}