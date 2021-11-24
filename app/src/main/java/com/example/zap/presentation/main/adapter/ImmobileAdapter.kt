package com.example.zap.presentation.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.zap.R
import com.example.zap.presentation.enum.BusinessType
import com.example.zap.presentation.formatArea
import com.example.zap.presentation.formatBathrooms
import com.example.zap.presentation.formatBedrooms
import com.example.zap.presentation.formatParking
import com.example.zap.presentation.model.ImmobileVO
import org.koin.core.KoinComponent

class ImmobileAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(), KoinComponent {

    companion object {
        private val HEADER = 0
        private val ITEM = 1
        val TRANSITION_NAME = "ITEM_TRANSITION"
    }

    private var immobiles = listOf<ImmobileVO>()
    lateinit var listenerZap: ButtonListener
    lateinit var listenerViva: ButtonListener
    lateinit var adapterListener: AdapterListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View
        val layoutInflater = LayoutInflater.from(parent.context)
        return when (viewType) {

            HEADER -> {
                view = layoutInflater.inflate(R.layout.adapter_header, parent, false)
                HeaderViewHolder(view)
            }
            else -> {
                view = layoutInflater.inflate(R.layout.adapter_immobile, parent, false)
                ImmobileViewHolder(view)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) { HEADER } else { ITEM }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder.itemViewType) {
            ITEM -> {
                val holder = holder as ImmobileViewHolder
                val item = immobiles[position - 1]
                holder.itemView.animation = AnimationUtils.loadAnimation(holder.itemView.context, R.anim.layout_animation)
                holder.bedroom.text = item.bedrooms.toString().formatBedrooms()
                holder.bathroom.text = item.bathrooms.toString().formatBathrooms()
                holder.area.text = item.usableArea.toString().formatArea()
                holder.garage.text = item.parkingSpaces.toString().formatParking()
                holder.itemView.setOnClickListener {
                    it.transitionName = TRANSITION_NAME
                    adapterListener.onSelectedItem(it, item)
                }
                when (item.pricingInfos.businessType) {
                    BusinessType.SALE -> {
                        holder.title.text = holder.itemView.context.getString(R.string.to_sale)
                    }
                    BusinessType.RENTAL -> {
                        holder.title.text = holder.itemView.context.getText(R.string.to_rental)
                    }
                }
                Glide
                    .with(holder.itemView.context)
                    .load(item.images.first())
                    .centerCrop()
                    .into(holder.photo)


            }
            HEADER -> {
                val holder = holder as HeaderViewHolder
                holder.vivaButton.setOnClickListener {
                    listenerViva.onClickButton(it)
                }
                holder.zapButton.setOnClickListener {
                    listenerZap.onClickButton(it)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return immobiles.count() + 1
    }

    fun setImmobiles(items: List<ImmobileVO>) {
        immobiles = items
        notifyDataSetChanged()
    }


}
