package moe.christina.decision.screen.activity

import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import moe.christina.mvp.android.support.ScreenAppCompatActivity
import javax.inject.Inject

abstract class BaseDecisionActivity :
    ScreenAppCompatActivity(),
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