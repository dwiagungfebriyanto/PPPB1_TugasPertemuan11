package com.example.themet

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.example.themet.databinding.ActivityArtworkDetailBinding

class ArtworkDetailActivity : AppCompatActivity() {
    val binding by lazy {
        ActivityArtworkDetailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        window.apply {
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor = Color.TRANSPARENT
        }
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val artworkImageUrl = intent.getStringExtra("artwork_image_url")
        val title = intent.getStringExtra("title")
        val artist = intent.getStringExtra("artist")
        val artistBio = intent.getStringExtra("artist_bio")
        val objectDate = intent.getStringExtra("object_date")
        val medium = intent.getStringExtra("medium")
        val dimensions = intent.getStringExtra("dimensions")

        with(binding) {
            Glide.with(this@ArtworkDetailActivity).load(artworkImageUrl).placeholder(R.drawable.placeholder_the_met).into(imgArtwork)
            txtArtwotkTitle.text = title
            txtArtist.text = artist
            txtArtistBio.text = artistBio
            txtObjectDate.text = objectDate
            txtMedium.text = medium
            txtDimensions.text = dimensions

            btnBack.setOnClickListener {
                onBackPressed()
            }
        }
    }
}