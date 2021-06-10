package com.trinityempire.capsulenytimes.model.entities

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class MovieCritics (
    @SerializedName("status") val status: String?,
    @SerializedName("copyright") val copyright: String?,
    @SerializedName("num_results") val num_results: Int?,
    @SerializedName("results") val results: ArrayList<MovieCriticsResults>?
)

data class MovieCriticsResults (
    @SerializedName("display_name") val display_name: String?,
    @SerializedName("sort_name") val sort_name: String?,
    @SerializedName("status") val status: String?,
    @SerializedName("bio") val bio: String?,
    @SerializedName("seo-name") val seoName: String?,
    @SerializedName("multimedia") val multimedia: MovieCriticsMultimedia?,
)

data class MovieCriticsMultimedia(
    @SerializedName("resource") val resource: MovieCriticsResource?
)

data class MovieCriticsResource(
    @SerializedName("type") val type: String?,
    @SerializedName("src") val src: String?,
    @SerializedName("height") val height: Int?,
    @SerializedName("width") val width: Int?,
    @SerializedName("credit") val credit: String?
)

