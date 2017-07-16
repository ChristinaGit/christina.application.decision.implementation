package moe.christina.decision;

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import moe.christina.decision.di.application.DaggerDecisionApplicationComponent
import javax.inject.Inject

class DecisionApplication : Application(), HasActivityInjector {
    @field:[Inject]
    lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> =
        dispatchingActivityInjector

    override fun onCreate() {
        super.onCreate()

        DaggerDecisionApplicationComponent
            .builder()
            .application(this)
            .build()
            .inject(this)
    }
}