package com.trinityempire.capsulenytimes.ui.gridview.adapter

import com.trinityempire.capsulenytimes.model.entities.MovieCriticsResults

interface GridViewAdapterListener {
    fun onUserClickListener(movieCritics: MovieCriticsResults)
}