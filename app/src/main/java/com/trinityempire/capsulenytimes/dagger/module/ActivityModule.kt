package com.trinityempire.capsulenytimes.dagger.module

import com.trinityempire.capsulenytimes.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity?
}