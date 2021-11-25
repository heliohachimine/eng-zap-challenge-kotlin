package com.example.zap.presentation.model

import com.example.zap.presentation.enum.ListingStatus
import com.example.zap.presentation.enum.ListingType

data class ImmobileVO(
    val usableArea: Double,
    val listingType: ListingType,
    val listingStatus: ListingStatus,
    val id: String,
    val parkingSpaces: Int,
    val owner: Boolean,
    val images: List<String>,
    val address: AddressVO,
    val bathrooms: Int,
    val bedrooms: Int,
    val pricingInfos: PricingInfoVO
) : ViewObject
