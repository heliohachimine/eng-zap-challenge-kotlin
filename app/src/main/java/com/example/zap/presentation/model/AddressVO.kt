package com.example.zap.presentation.model

data class AddressVO(
    val city: String,
    val neighborhood: String,
    val lat: Double,
    val lng: Double
) : ViewObject
