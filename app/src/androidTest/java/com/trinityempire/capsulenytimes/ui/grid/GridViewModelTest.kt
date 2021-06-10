package com.trinityempire.capsulenytimes.ui.grid

import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.trinityempire.capsulenytimes.MainApplication
import com.trinityempire.capsulenytimes.model.entities.MovieCriticsMultimedia
import com.trinityempire.capsulenytimes.model.entities.MovieCriticsResource
import com.trinityempire.capsulenytimes.model.entities.MovieCriticsResults
import com.trinityempire.capsulenytimes.ui.gridview.GridViewModel
import com.trinityempire.capsulenytimes.ui.gridview.GridViewModelState
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class GridViewModelTest: TestCase() {

    lateinit var viewModel: GridViewModel

    @Before
    fun setup() {
        viewModel = GridViewModel()
        val app = getApplicationContext<MainApplication>()
        viewModel.applicationContext = app
    }

    @Test
    fun getGridNumberOfRowsTest_defaultOrientation_IsPortrait() {
        assertEquals(viewModel.getGridNumberOfRow(), GridViewModel.PORTRAIT_ROW)
    }

    @Test
    fun isLoadingStatus_isEmitted() {
        // .value should be called from the main thread
        // in order to test, decided to test on main thread.
        GlobalScope.launch(Dispatchers.Main) {
            viewModel.gridViewModelStates.observeForever {
                assertEquals(it, GridViewModelState.Loading(true))
            }
            viewModel.gridViewModelStates.value = (GridViewModelState.Loading(true))
        }
    }

    @Test
    fun verifyMovieCriticsLoadError_isEmitted() {
        GlobalScope.launch(Dispatchers.Main) {
            // observe the state
            viewModel.gridViewModelStates.observeForever{
                // verify
                assertEquals(it, GridViewModelState.MovieCriticsLoadError("Error"))
            }
            // post the value
            viewModel.gridViewModelStates.value = GridViewModelState.MovieCriticsLoadError("Error")
        }
    }

    @Test
    fun verifyMovieCritics_isEmitted() {
        val movieCritics = GridViewModelState.MovieCritics(movieCritic)
        GlobalScope.launch(Dispatchers.Main) {
            viewModel.gridViewModelStates.observeForever {
                assertEquals(it, movieCritics)
            }

            viewModel.gridViewModelStates.value = movieCritics
        }
    }

    val movieCritic = arrayListOf<MovieCriticsResults>(MovieCriticsResults(
        "name", "name", "name", "name","",
        MovieCriticsMultimedia(resource =
        MovieCriticsResource("","",90,90,"") )))
}
