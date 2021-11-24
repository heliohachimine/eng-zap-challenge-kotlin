package com.example.zap.presentation.details.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.zap.R
import org.koin.core.KoinComponent

class ImageAdapter : RecyclerView.Adapter<ImageViewHolder>(), KoinComponent {

    private var imageUrls = listOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.adapter_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val url = imageUrls[position]
        Glide
            .with(holder.itemView.context)
            .load(url)
            .centerCrop()
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return imageUrls.count()
    }

    fun setupImageUrls(imageUrls: List<String>) {
        this.imageUrls = imageUrls
        notifyDataSetChanged()
    }
}
