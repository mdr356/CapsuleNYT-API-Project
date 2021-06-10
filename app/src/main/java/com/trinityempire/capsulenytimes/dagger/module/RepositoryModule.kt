package com.trinityempire.capsulenytimes.dagger.module

import com.trinityempire.capsulenytimes.model.remote.RetrofitApi
import com.trinityempire.capsulenytimes.model.repository.CriticsRepository
import com.trinityempire.capsulenytimes.model.repository.CriticsRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun bindCriticsRepository(retrofit: RetrofitApi) : CriticsRepository {
        return CriticsRepositoryImpl(retrofit)
    }
}