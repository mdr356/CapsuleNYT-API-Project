package com.trinityempire.capsulenytimes.dagger.component

import android.app.Application
import android.content.Context
import com.trinityempire.capsulenytimes.MainApplication
import com.trinityempire.capsulenytimes.dagger.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        FragmentModule::class,
        NetworkModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
    ]
)
interface AppComponent : AndroidInjector<MainApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }
}
