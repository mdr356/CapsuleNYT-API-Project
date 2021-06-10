package com.trinityempire.capsulenytimes.model.remote


import com.trinityempire.capsulenytimes.model.entities.MovieCritics
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {

    @GET("critics/all.json")
    suspend fun fetchAllCritics(): MovieCritics?
}