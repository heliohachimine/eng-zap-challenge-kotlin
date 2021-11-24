package com.example.zap.presentation.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.zap.R
import com.example.zap.core.Either
import com.example.zap.presentation.details.adapter.ImageAdapter
import com.example.zap.presentation.enum.BusinessType
import com.example.zap.presentation.formatArea
import com.example.zap.presentation.formatBathrooms
import com.example.zap.presentation.formatBedrooms
import com.example.zap.presentation.formatParking
import com.example.zap.presentation.main.MainActivity
import com.example.zap.presentation.model.ImmobileVO
import com.example.zap.presentation.toCurrencyBr
import kotlinx.android.synthetic.main.activity_details.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class DetailsActivity : AppCompatActivity() {

    private val adapter: ImageAdapter by inject()
    private val viewModel: DetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setupImages()
        getDetail()
        subscribeLiveData()
    }

    private fun getDetail() {
        val immobileId = intent.getStringExtra(MainActivity.EXTRA_ID)
        immobileId?.let { viewModel.getImmobile(it) }
        loading_details.visibility = View.VISIBLE
    }

    private fun setupImages() {
        rv_images.adapter = adapter
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        rv_images.layoutManager = layoutManager
    }

    private fun subscribeLiveData() {
        viewModel.immobileLiveData.observe(
            this,
            Observer {
                loading_details.visibility = View.INVISIBLE
                when (it) {
                    is Either.Left -> {
                    }
                    is Either.Right -> {
                        it.value?.let { immobile ->
                            setImmobile(immobile)
                        }
                    }
                }
            }
        )
    }

    private fun setImmobile(immobile: ImmobileVO) {
        adapter.setupImageUrls(immobile.images)
        when (immobile.pricingInfos.businessType) {
            BusinessType.SALE -> {
                tv_title.text = getString(R.string.to_sale)
            }
            BusinessType.RENTAL -> {
                tv_title.text = getString(R.string.to_rental)
            }
        }
        tv_bedroom.text = immobile.bedrooms.toString().formatBedrooms()
        tv_bathroom.text = immobile.bathrooms.toString().formatBathrooms()
        tv_area.text = immobile.usableArea.toString().formatArea()
        tv_garage.text = immobile.parkingSpaces.toString().formatParking()
        tv_address.text = "${immobile.address.neighborhood} - ${immobile.address.city}"
        when (immobile.pricingInfos.businessType) {
            BusinessType.RENTAL -> {
                tv_price.text = "${immobile.pricingInfos.price.toCurrencyBr()} / Mês"
            }
            BusinessType.SALE -> {
                tv_price.text = immobile.pricingInfos.price.toCurrencyBr()
            }
        }

        bnt_map.setOnClickListener {
            val lat = immobile.address.geolocation.location.lat
            val lng = immobile.address.geolocation.location.lon
            val gmmIntentUri = Uri.parse("geo:${lat},${lng}?q=$lat,$lng")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(mapIntent)
        }
    }
}
