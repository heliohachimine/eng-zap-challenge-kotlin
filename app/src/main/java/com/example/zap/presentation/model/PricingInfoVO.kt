package com.example.zap.presentation.model

import com.example.zap.presentation.enum.BusinessType

data class PricingInfoVO(
    val iptu: String?,
    val price: Double,
    val businessType: BusinessType,
    val monthlyCondoFee: Double
) : ViewObject
