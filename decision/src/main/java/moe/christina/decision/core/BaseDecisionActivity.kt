package moe.christina.decision.core

import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import moe.christina.common.android.support.ObservableAppCompatActivity
import javax.inject.Inject

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
    }
}