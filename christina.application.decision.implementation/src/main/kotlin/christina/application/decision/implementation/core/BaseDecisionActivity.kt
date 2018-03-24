package christina.application.decision.implementation.core

import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import christina.application.decision.implementation.core.qualifier.ScopeName
import christina.library.android.architecture.mvp.presenter.ScreenObserver
import christina.library.android.common.support.ObservableAppCompatActivity
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject
import javax.inject.Named

abstract class BaseDecisionActivity :
    ObservableAppCompatActivity(),
    HasSupportFragmentInjector {
    @field:[Inject]
    lateinit var supportFragmentInjector: DispatchingAndroidInjector<Fragment>

    override fun supportFragmentInjector(): AndroidInjector<Fragment> =
        supportFragmentInjector

    @CallSuper
    override fun onInjectMembers() {
        super.onInjectMembers()

        AndroidInjection.inject(this)

        observer.subscribe()
    }

    override fun onReleaseInjectedMembers() {
        super.onReleaseInjectedMembers()

        observer.unsubscribe()
    }

    @field:[Inject Named(ScopeName.ACTIVITY)]
    lateinit var observer: ScreenObserver
}