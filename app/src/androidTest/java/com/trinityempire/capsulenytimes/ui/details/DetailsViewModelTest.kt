package com.trinityempire.capsulenytimes.ui.details

import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DetailsViewModelTest: TestCase() {
    lateinit var viewModel: DetailsViewModel

    lateinit var aboutCriticData : DetailsFragment.Companion.AboutCriticData

    @Before
    fun setup() {
        viewModel = DetailsViewModel()
        aboutCriticData = DetailsFragment.Companion.AboutCriticData(
            "name", "sort name", "status", "bio", "seo name",)
        viewModel.aboutCritic = aboutCriticData
    }

    @Test
    fun readAboutCritic_displayName_isCorrect() {
        assertEquals(aboutCriticData.display_name, "name")
    }

    @Test
    fun readAboutCritic_srcIsNull() {
        assertNull(viewModel.aboutCritic.multimedia)
    }
}
