package com.trinityempire.capsulenytimes.model.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.gson.Gson
import com.trinityempire.capsulenytimes.model.entities.MovieCritics
import com.trinityempire.capsulenytimes.model.repository.MockInterceptor.Companion.movieCriticLocalData
import com.trinityempire.capsulenytimes.model.repository.helper.NetworkClientHelper
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CriticsRepositoryTest: TestCase() {

    lateinit var criticsRepository: CriticsRepository

    val api = NetworkClientHelper.create()
    @Before
    fun setup() {
        criticsRepository = CriticsRepositoryImpl(api)
    }

    @Test
    fun verifyFetchAllCriticsApiCall() {
        GlobalScope.launch(Dispatchers.Main) {
            val movieCriticsObject = Gson().fromJson(movieCriticLocalData, MovieCritics::class.java)
            assertEquals(criticsRepository.fetchAllCritics(), movieCriticsObject)
        }
    }
}