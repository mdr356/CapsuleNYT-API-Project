package com.trinityempire.capsulenytimes.ui.gridview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.trinityempire.capsulenytimes.R
import com.trinityempire.capsulenytimes.databinding.FragmentGridBinding
import com.trinityempire.capsulenytimes.model.entities.MovieCriticsResults
import com.trinityempire.capsulenytimes.ui.details.DetailsFragment
import com.trinityempire.capsulenytimes.ui.gridview.adapter.GridViewAdapter
import com.trinityempire.capsulenytimes.ui.gridview.adapter.GridViewAdapterListener
import dagger.android.support.DaggerFragment
import timber.log.Timber
import javax.inject.Inject

class GridViewFragment : DaggerFragment(), GridViewAdapterListener, SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var viewModel: GridViewModel

    private var _binding: FragmentGridBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var gridViewAdapter = GridViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Timber.d("On Create View")
        _binding = FragmentGridBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("On View Created")

        viewModel.gridViewModelStates.observe(this as LifecycleOwner, {
            when(it) {
                is GridViewModelState.Loading -> {
                    Timber.d("Movie Critics Loading")
                    it.isLoading.let {
                        _binding?.loadingView?.visibility = if(it) View.VISIBLE else View.GONE
                        if(it) {
                            _binding?.listError?.visibility = View.GONE
                            _binding?.recycleViewContainer?.visibility = View.GONE
                        }
                    }
                }
                is GridViewModelState.MovieCriticsLoadError -> {
                    Timber.d("Movie Critics Load Error")
                    _binding?.listError?.visibility = if(it.errorMsg == "") View.GONE else View.VISIBLE
                }
                is GridViewModelState.MovieCritics -> {
                    Timber.d("Movie Critics Loaded Successful")
                    _binding?.recycleViewContainer?.visibility = View.VISIBLE
                    gridViewAdapter.updateCriticsList(it.critics) }
                }
            })
        // start fetching from api
        viewModel.fetchAllCritrics()
        // gridlayout
        // call viewModel.getGridNumberOfRow, to get the row base on orientation
        val gridLayoutManager = GridLayoutManager(context, viewModel.getGridNumberOfRow())
        _binding?.recyclerView?.layoutManager = gridLayoutManager // set LayoutManager to RecyclerView
        // User click
        gridViewAdapter.setListener(this)
        _binding?.recyclerView?.adapter = gridViewAdapter
        // allow user to reload the data once data has been loaded.
        _binding?.recycleViewContainer?.setOnRefreshListener(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.d("On Destroy View")
        _binding = null
    }

    override fun onUserClickListener(movieCritics: MovieCriticsResults) {
        Timber.d("User selected")
        Timber.d(movieCritics.display_name)
        val aboutCritic = DetailsFragment.Companion.AboutCriticData().copy(display_name = movieCritics.display_name,
            sort_name = movieCritics.sort_name, status = movieCritics.sort_name,
            bio = movieCritics.bio, seoName = movieCritics.seoName,
            multimedia = DetailsFragment.Companion.AboutCriticData.MovieCriticsMultimedia(
                type = movieCritics.multimedia?.resource?.type,
                src = movieCritics.multimedia?.resource?.src,
                height = movieCritics.multimedia?.resource?.height,
                width = movieCritics.multimedia?.resource?.width,
                credit = movieCritics.multimedia?.resource?.credit,
            ),
        )
        val bundle = bundleOf(DetailsFragment.arg to aboutCritic)
        findNavController().navigate(R.id.action_GridFragment_to_DetailsFragment, bundle)
    }

    override fun onRefresh() {
        Timber.d("On Refresh")
        viewModel.fetchAllCritrics()
        if (_binding?.recycleViewContainer?.isRefreshing() == true) {
            _binding?.recycleViewContainer?.isRefreshing = false;
        }
    }
}