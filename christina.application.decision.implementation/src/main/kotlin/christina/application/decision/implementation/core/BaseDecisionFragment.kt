package christina.application.decision.implementation.core

import android.support.annotation.CallSuper
import dagger.android.support.AndroidSupportInjection
import christina.library.android.common.support.ObservableFragment
import christina.application.decision.implementation.core.qualifier.ScopeName
import christina.library.android.architecture.mvp.presenter.ScreenObserver
import javax.inject.Inject
import javax.inject.Named

abstract class BaseDecisionFragment :
    ObservableFragment() {
    @CallSuper
    override fun onInjectMembers() {
        super.onInjectMembers()

        AndroidSupportInjection.inject(this)

        observer.subscribe()
    }

    override fun onReleaseInjectedMembers() {
        super.onReleaseInjectedMembers()

        observer.unsubscribe()
    }

    @field:[Inject Named(ScopeName.FRAGMENT)]
    lateinit var observer: ScreenObserver
}