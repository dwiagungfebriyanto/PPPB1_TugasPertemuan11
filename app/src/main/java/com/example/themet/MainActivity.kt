package com.example.themet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.themet.databinding.ActivityMainBinding
import com.example.themet.model.Artwork
import com.example.themet.model.Artworks
import com.example.themet.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    var listOfArtworks = arrayListOf<Artwork>()

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val adapterArtwork = ArtworkAdapter(generateArtworks()) { artwork ->
            Toast.makeText(this@MainActivity, artwork.title, Toast.LENGTH_SHORT).show()

            val intentToArtworkDetailActivity = Intent(this@MainActivity, ArtworkDetailActivity::class.java)

            intentToArtworkDetailActivity.putExtra("artwork_image_url", artwork.primaryImageSmall)
            intentToArtworkDetailActivity.putExtra("title", artwork.title)
            intentToArtworkDetailActivity.putExtra("artist", artwork.artistDisplayName)
            intentToArtworkDetailActivity.putExtra("artist_bio", artwork.artistDisplayBio)
            intentToArtworkDetailActivity.putExtra("object_date", artwork.objectDate)
            intentToArtworkDetailActivity.putExtra("medium", artwork.medium)
            intentToArtworkDetailActivity.putExtra("dimensions", artwork.dimensions)

            startActivity(intentToArtworkDetailActivity)
        }

        val client = ApiClient.getInstance()
        val listIds = client.getArtworkIDs("korea", true)

        listIds.enqueue(object : Callback<Artworks> {
            override fun onResponse(call: Call<Artworks>, response: Response<Artworks>) {
                if (response.isSuccessful) {
                    for (i in response.body()?.objectIDs ?: arrayListOf()) {
                        if (listOfArtworks.size < 20) {
                            addToArtworksList(i.toString())
                        } else {
                            break
                        }
                    }
                    adapterArtwork.notifyDataSetChanged()
                    binding.progressBar.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<Artworks>, t: Throwable) {}
        })

        with(binding) {
            rvArtworks.apply {
                adapter = adapterArtwork

                layoutManager = LinearLayoutManager(this@MainActivity)
            }
        }
    }

    fun addToArtworksList(objectID: String) {
        val client = ApiClient.getInstance()
        val responses = client.getArtwork(objectID)

        responses.enqueue(object : Callback<Artwork> {
            override fun onResponse(call: Call<Artwork>, response: Response<Artwork>) {
                if (response.isSuccessful) {
                    val artwork = response.body()
                    if (listOfArtworks.size < 20) {
                        if (
                            artwork?.primaryImageSmall != "" &&
                            artwork?.title != "" &&
                            artwork?.artistDisplayName != "" &&
                            artwork?.objectDate != "" &&
                            artwork?.medium != "" &&
                            artwork?.artistDisplayBio != "" &&
                            artwork?.dimensions != ""
                            ) {
                            listOfArtworks.add(artwork!!)
                        }
                    }
                }
            }

            override fun onFailure(call: Call<Artwork>, t: Throwable) {}
        })
    }

    fun generateArtworks(): List<Artwork> {
        return listOfArtworks
    }
}