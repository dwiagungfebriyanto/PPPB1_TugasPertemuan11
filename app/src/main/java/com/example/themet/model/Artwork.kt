package com.example.themet.model

import com.google.gson.annotations.SerializedName

data class Artwork(
    @SerializedName("primaryImageSmall")
    val primaryImageSmall: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("artistDisplayName")
    val artistDisplayName: String,

    @SerializedName("artistDisplayBio")
    val artistDisplayBio: String,

    @SerializedName("objectDate")
    val objectDate: String,

    @SerializedName("medium")
    val medium: String,

    @SerializedName("dimensions")
    val dimensions: String
)
