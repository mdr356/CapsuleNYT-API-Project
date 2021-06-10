package com.trinityempire.capsulenytimes.dagger.module

import com.trinityempire.capsulenytimes.model.remote.RetrofitApi
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level.BODY
import retrofit2.Retrofit
import retrofit2.Retrofit.Builder
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class NetworkModule {

    //                .addQueryParameter("apikey", "your-actual-api-key")
    companion object {
        val BASE_URL = "https://api.nytimes.com/svc/movies/v2/"
        private val API_KEY = "cPnm2tEp2y9O1ou1DwV4EXyHLyYg0S2s"
    }
    @Provides
    @Singleton
    fun getRetrofitApi(retroFit: Retrofit): RetrofitApi {
        return retroFit.create(RetrofitApi::class.java)
    }

    @Provides
    fun getRetrofit(okHttpClient: OkHttpClient?): Retrofit {
        return Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val original: Request = chain.request()
                val originalHttpUrl: HttpUrl = original.url
                val url = originalHttpUrl.newBuilder()
                        // Add the same query parameter to every request
                    .addQueryParameter("api-key", API_KEY)
                    .build()

                // Request customization: adding request header
                val requestBuilder: Request.Builder = original.newBuilder()
                    .url(url)
                val request: Request = requestBuilder.build()
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(BODY)
        return httpLoggingInterceptor
    }
}