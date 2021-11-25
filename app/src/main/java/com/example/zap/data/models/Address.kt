package com.example.zap.data.models

import com.example.zap.presentation.model.AddressVO
import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("city") val city: String,
    @SerializedName("neighborhood") val neighborhood: String,
    @SerializedName("geoLocation") val geolocation: GeoLocation,
) : DataObject {
    override fun toViewObject(): AddressVO {
        return AddressVO(city, neighborhood, geolocation.location.lat, geolocation.location.lon)
    }
}
