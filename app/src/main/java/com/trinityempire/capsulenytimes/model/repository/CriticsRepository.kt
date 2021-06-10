package com.trinityempire.capsulenytimes.model.repository

import com.trinityempire.capsulenytimes.model.entities.MovieCritics
import com.trinityempire.capsulenytimes.model.remote.RetrofitApi
import timber.log.Timber
import javax.inject.Inject

interface CriticsRepository {
    suspend fun fetchAllCritics() : MovieCritics?
}

class CriticsRepositoryImpl @Inject constructor(val request: RetrofitApi) : CriticsRepository {

    override suspend fun fetchAllCritics(): MovieCritics? {
        Timber.d("Fetch All Critics API call")
        return request.fetchAllCritics()
    }

}
