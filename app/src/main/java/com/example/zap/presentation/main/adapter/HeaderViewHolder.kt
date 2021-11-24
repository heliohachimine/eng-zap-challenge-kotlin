package com.example.zap.presentation.main.adapter

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.zap.R

class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val vivaButton: ImageView = itemView.findViewById(R.id.iv_logo_viva_real)
    val zapButton: ImageView = itemView.findViewById(R.id.iv_logo_zap)
}
