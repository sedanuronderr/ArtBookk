package com.seda.artbookk.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.seda.artbookk.databinding.ArtRowBinding
import com.seda.artbookk.databinding.ImageRowBinding
import com.seda.artbookk.roomdb.Art
import javax.inject.Inject

class ImageRecyclerAdapter @Inject constructor() :RecyclerView.Adapter<ImageRecyclerAdapter.ImageViewHolder>() {

private var onItemClickListener : ((String)->Unit)? = null


    class ImageViewHolder(val binding:ImageRowBinding):RecyclerView.ViewHolder(binding.root) {

    }
    private val differCallback = object : DiffUtil.ItemCallback<String>() {

        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }


    fun setOnItemClickListener(listener : (String) -> Unit) {
        onItemClickListener = listener
    }
    private val differ = AsyncListDiffer(this, differCallback)
    var images : List<String>
        get() = differ.currentList
        set(value) = differ.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(ImageRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {

        val images = differ.currentList[position]
        holder.binding.apply {
            Glide.with(holder.itemView).load(images).into(singleArtImageView)
        }
        holder.itemView.setOnClickListener {

            onItemClickListener?.let { it1 -> it1(images) }

        }
    }

    override fun getItemCount(): Int {
      return differ.currentList.size

    }


}


