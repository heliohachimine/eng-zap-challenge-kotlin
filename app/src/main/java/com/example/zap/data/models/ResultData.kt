package com.example.zap.data.models

import com.example.zap.presentation.enum.ListingStatus
import com.example.zap.presentation.enum.ListingType
import com.example.zap.presentation.model.ImmobileVO
import com.google.gson.annotations.SerializedName
import java.util.Date

data class ResultData(
    @SerializedName("usableAreas") val usableAreas: Double,
    @SerializedName("listingType") val listingType: String,
    @SerializedName("createdAt") val createdAt: Date,
    @SerializedName("listingStatus") val listingStatus: String,
    @SerializedName("id") val id: String,
    @SerializedName("parkingSpaces") val parkingSpaces: Int,
    @SerializedName("updatedAt") val updatedAt: Date,
    @SerializedName("owner") val owner: Boolean,
    @SerializedName("images") val images: List<String>,
    @SerializedName("address") val address: Address,
    @SerializedName("bathrooms") val bathrooms: Int,
    @SerializedName("bedrooms") val bedrooms: Int,
    @SerializedName("pricingInfos") val pricingInfos: PricingInfos
) : DataObject {
    override fun toViewObject(): ImmobileVO {
        return ImmobileVO(
            usableArea = usableAreas,
            listingType = ListingType.valueOf(listingType),
            listingStatus = ListingStatus.valueOf(listingStatus),
            id = id,
            parkingSpaces = parkingSpaces,
            owner = owner,
            images = images,
            address = address.toViewObject(),
            bathrooms = bathrooms,
            bedrooms = bedrooms,
            pricingInfos = pricingInfos.toViewObject()
        )
    }
}

data class GeoLocation(
    @SerializedName("precision") val precision: String,
    @SerializedName("location") val location: Location
)

data class Location(
    @SerializedName("lon") val lon: Double,
    @SerializedName("lat") val lat: Double
)
