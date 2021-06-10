package com.trinityempire.capsulenytimes.ui.gridview

import android.app.Application
import android.content.res.Configuration
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.trinityempire.capsulenytimes.R
import com.trinityempire.capsulenytimes.model.entities.MovieCriticsResults
import com.trinityempire.capsulenytimes.model.repository.CriticsRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class GridViewModel @Inject constructor() : ViewModel() {

    @Inject
    lateinit var applicationContext: Application

    companion object {
        const val PORTRAIT_ROW = 3
        const val LANDSCAPE_ROW = 5
    }
    @Inject
    lateinit var criticsRepository: CriticsRepository

    // Observable
    // view only observe this sealed class and receive updates.
    val gridViewModelStates = MutableLiveData<GridViewModelState>()

    fun fetchAllCritrics() {
        gridViewModelStates.value = GridViewModelState.Loading(true)

        Timber.d("Fetch All Critics")
        // using viewModelScope so coroutine launched can automatically canceled if the ViewModel is cleared
        viewModelScope.launch {
            // make api call
            val response = criticsRepository.fetchAllCritics()
            val gson = Gson().toJson(response)
            Timber.d(gson)
            response?.results.let {
                when(it) {
                    null -> {
                        Timber.e("Movie Critics Loading error")
                        gridViewModelStates.value = GridViewModelState.MovieCriticsLoadError(applicationContext.resources.getString(R.string.errorLoading))
                        gridViewModelStates.value = GridViewModelState.Loading(false)
                    }
                    else -> {
                        Timber.d("Movie Critics Loaded Successful")

                        gridViewModelStates.value = GridViewModelState.MovieCritics(it)
                        gridViewModelStates.value = GridViewModelState.Loading(false)
                    }
                }

            }
        }
    }

    fun getGridNumberOfRow(): Int {
        Timber.d("Get Grid Number of Row")
        val orientation = applicationContext.resources.configuration.orientation
        return if(orientation == Configuration.ORIENTATION_PORTRAIT) PORTRAIT_ROW
        else LANDSCAPE_ROW
    }
}

sealed class GridViewModelState {
    data class MovieCritics(val critics: List<MovieCriticsResults>): GridViewModelState()
    data class MovieCriticsLoadError(val errorMsg: String): GridViewModelState()
    data class Loading(val isLoading: Boolean): GridViewModelState()
}
