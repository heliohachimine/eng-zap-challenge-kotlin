package com.example.zap.domain

import com.example.zap.presentation.enum.BusinessType

object UseCases {
    val PERCENT_MAX_CONDO_FEE = 30.0
    val PERCENT_MAX_CONDO_FEE_EXCEPTION = 50.0
    val VALUE_MIN_AREA = 3500.0
    val LAT_MAX = -23.546686
    val LAT_MIN = -23.568704
    val LNG_MAX = -46.641146
    val LNG_MIN = -46.693419

    fun checkVivaImmobile(businessType: BusinessType, condoFee: Double, price: Double, lat: Double, lng: Double): Boolean {
        return if (insideBoundBoxZap(lat, lng)) {
            businessType == BusinessType.RENTAL && calcCondoFee(price, condoFee) < PERCENT_MAX_CONDO_FEE_EXCEPTION
        } else {
            businessType == BusinessType.RENTAL && calcCondoFee(price, condoFee) < PERCENT_MAX_CONDO_FEE
        }

    }

    fun checkZapImmobile(businessType: BusinessType, price: Double, usableArea: Double): Boolean {
        return businessType == BusinessType.SALE && calcAreaPrice(price, usableArea) > VALUE_MIN_AREA
    }

    private fun insideBoundBoxZap(lat: Double, lng: Double): Boolean {
        return lat > LAT_MIN && lat < LAT_MAX && lng > LNG_MIN && lng < LNG_MAX
    }

    private fun calcCondoFee(price: Double, condoFee: Double): Double {
        return condoFee / price * 100
    }

    private fun calcAreaPrice(totalValue: Double, totalArea: Double): Double {
        return totalValue / totalArea
    }
}