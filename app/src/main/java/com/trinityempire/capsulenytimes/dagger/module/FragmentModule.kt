package com.trinityempire.capsulenytimes.dagger.module

import com.trinityempire.capsulenytimes.ui.details.DetailsFragment
import com.trinityempire.capsulenytimes.ui.gridview.GridViewFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {
    @ContributesAndroidInjector
    abstract fun bindGridViewFragment() : GridViewFragment

    @ContributesAndroidInjector
    abstract fun bindDetailsFragment(): DetailsFragment
}