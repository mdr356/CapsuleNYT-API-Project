package com.trinityempire.capsulenytimes.ui.details

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.annotations.SerializedName
import com.squareup.picasso.Picasso
import com.trinityempire.capsulenytimes.R
import com.trinityempire.capsulenytimes.databinding.FragmentDetailsBinding
import com.trinityempire.capsulenytimes.model.entities.MovieCriticsMultimedia
import com.trinityempire.capsulenytimes.ui.gridview.adapter.GridViewAdapter
import dagger.android.support.DaggerFragment
import kotlinx.android.parcel.Parcelize
import javax.inject.Inject


class DetailsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModel: DetailsViewModel

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        // Set data into view model
        arguments?.getParcelable<AboutCriticData>(arg).let {
            viewModel.aboutCritic = it!!
        }

        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding?.displayName?.text = viewModel.aboutCritic.display_name
        _binding?.webviewBio?.loadData(viewModel.aboutCritic.bio!!, "text/html",
            "utf-8")
        _binding?.webviewBio?.setVerticalScrollBarEnabled(true)


        viewModel.aboutCritic.multimedia?.width?.let { width ->
            viewModel.aboutCritic.multimedia?.height?.let { height ->
                Picasso.get()
                    .load(viewModel.aboutCritic.multimedia?.src)
                    .resize(width, height)
                    .placeholder(R.drawable.placeholder)
                    .centerCrop()
                    .into(_binding?.imageView)
            }
        }
        /*binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val arg = "aboutCritic"
        @Parcelize
        data class AboutCriticData (
            val display_name: String? = null,
            val sort_name: String? = null,
            val status: String? = null,
            val bio: String? = null,
            val seoName: String? = null,
            val multimedia: MovieCriticsMultimedia? = null,
        ): Parcelable {
            @Parcelize
            data class MovieCriticsMultimedia(
                val type: String? = null,
                val src: String? = null,
                val height: Int? = null,
                val width: Int? = null,
                val credit: String?
                ) : Parcelable
        }
    }
}
