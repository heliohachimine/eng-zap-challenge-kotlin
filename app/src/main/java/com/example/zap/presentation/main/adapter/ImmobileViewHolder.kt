package com.example.zap.presentation.main.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.zap.R

class ImmobileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val photo: ImageView = itemView.findViewById(R.id.iv_photo)
    val title: TextView = itemView.findViewById(R.id.tv_title_card)
    val bedroom: TextView = itemView.findViewById(R.id.tv_bedroom)
    val bathroom: TextView = itemView.findViewById(R.id.tv_bathroom)
    val area: TextView = itemView.findViewById(R.id.tv_area)
    val garage: TextView = itemView.findViewById(R.id.tv_garage)
}
