package com.trinityempire.capsulenytimes

import android.app.Application
import com.trinityempire.capsulenytimes.dagger.component.AppComponent
import com.trinityempire.capsulenytimes.dagger.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject


class MainApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector : DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        // This will initialise Timber
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
            Timber.d("DEBUG MODE")
        }
        val mComponent: AppComponent = DaggerAppComponent.builder().application(this).context(this).build()
        mComponent.inject(this)
    }

    override fun androidInjector(): AndroidInjector<Any> = androidInjector

}