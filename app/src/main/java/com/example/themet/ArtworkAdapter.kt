package com.example.themet

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.themet.databinding.ItemArtworkBinding
import com.example.themet.model.Artwork

typealias OnClickArtwork = (Artwork) -> Unit

class ArtworkAdapter(private val listArtwork: List<Artwork>, private val onClickArtwork: OnClickArtwork) : RecyclerView.Adapter<ArtworkAdapter.ItemArtworkViewHolder>() {

    inner class ItemArtworkViewHolder(private val binding: ItemArtworkBinding) :
            RecyclerView.ViewHolder(binding.root) {

                fun bind(data : Artwork) {
                    with(binding) {
                        Glide.with(binding.root).load(data.primaryImageSmall).placeholder(R.drawable.placeholder_the_met).into(imgArtwork)
                        txtArtwotkTitle.text = data.title
                        txtArtist.text = data.artistDisplayName

                        itemView.setOnClickListener {
                            onClickArtwork(data)
                        }
                    }
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemArtworkViewHolder {
        val binding = ItemArtworkBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemArtworkViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listArtwork.size
    }

    override fun onBindViewHolder(holder: ItemArtworkViewHolder, position: Int) {
        holder.bind(listArtwork[position])
    }
}