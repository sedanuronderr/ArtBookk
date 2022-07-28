package com.seda.artbookk.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.seda.artbookk.GlideLoader
import com.seda.artbookk.databinding.ArtRowBinding
import com.seda.artbookk.model.Hit
import com.seda.artbookk.roomdb.Art
import javax.inject.Inject

class ArtRecyclerView @Inject constructor(): RecyclerView.Adapter<ArtRecyclerView.ArtView>() {

    inner class ArtView(val binding:ArtRowBinding): RecyclerView.ViewHolder(binding.root){

    }

    private val differCallback = object : DiffUtil.ItemCallback<Art>() {

        override fun areItemsTheSame(oldItem:Art, newItem: Art): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Art, newItem: Art): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
    fun submitList(list: List<Art>) = differ.submitList(list)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtView {
        return  ArtView(ArtRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ArtView, position: Int) {
        val art = differ.currentList[position]
        holder.binding.apply {
            artRowArtNameText.text = art.name
            artRowYearText.text= art.year.toString()
            artRowArtistNameText.text = art.artistName
            Glide.with(holder.itemView).load(art.imageUrl).into(artRowImageView)
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}