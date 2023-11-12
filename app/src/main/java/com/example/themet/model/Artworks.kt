package com.example.themet.model

import com.google.gson.annotations.SerializedName

data class Artworks(
    @SerializedName("objectIDs")
    val objectIDs: List<Int>
)
