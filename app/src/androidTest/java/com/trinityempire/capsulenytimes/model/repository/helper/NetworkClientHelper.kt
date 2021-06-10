package com.trinityempire.capsulenytimes.model.repository.helper

import com.trinityempire.capsulenytimes.dagger.module.NetworkModule
import com.trinityempire.capsulenytimes.model.remote.RetrofitApi
import com.trinityempire.capsulenytimes.model.repository.MockInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkClientHelper {

    fun create(): RetrofitApi {

        return Retrofit.Builder()
            .client(OkHttpClient.Builder().addInterceptor(MockInterceptor()).build())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(NetworkModule.BASE_URL)
            .build().create(RetrofitApi::class.java)
    }
}