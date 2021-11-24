package com.example.zap.data.models

import com.example.zap.presentation.enum.BusinessType
import com.example.zap.presentation.model.PricingInfoVO
import com.google.gson.annotations.SerializedName

data class PricingInfos(
    @SerializedName("yearlyIptu") val iptu: String?,
    @SerializedName("price") val price: Double,
    @SerializedName("businessType") val businessType: String,
    @SerializedName("monthlyCondoFee") val monthlyCondoFee: Double
) : DataObject {
    override fun toViewObject(): PricingInfoVO {
        return PricingInfoVO(
            iptu = iptu,
            price = price,
            businessType = BusinessType.valueOf(businessType),
            monthlyCondoFee = monthlyCondoFee
        )
    }
}
