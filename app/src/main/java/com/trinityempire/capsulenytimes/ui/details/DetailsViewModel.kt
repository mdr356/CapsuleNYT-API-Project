package com.trinityempire.capsulenytimes.ui.details

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class DetailsViewModel @Inject constructor() : ViewModel() {
    lateinit var aboutCritic: DetailsFragment.Companion.AboutCriticData
}