package com.example.themet.network

import com.example.themet.model.Artwork
import com.example.themet.model.Artworks
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search")
    fun getArtworkIDs(
        @Query("q") keyword: String,
        @Query("hasImages") hasImages: Boolean
    ): Call<Artworks>

    @GET("objects/{objectID}")
    fun getArtwork(
        @Path("objectID") objectID: String
    ): Call<Artwork>
}