package com.example.zap.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zap.core.Either
import com.example.zap.domain.Repository
import com.example.zap.presentation.enum.BusinessType
import com.example.zap.presentation.enum.ListType
import com.example.zap.presentation.model.ImmobileVO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MainViewModel(private val repository: Repository) : ViewModel() {
    companion object {
        val PERCENT_MAX_CONDO_FEE = 30.0
        val VALUE_MIN_AREA = 3500.0
        val LAT_MAX = -23.546686
        val LAT_MIN = -23.568704
        val LNG_MAX = -46.641146
        val LNG_MIN = -46.693419
    }

    val dataLiveData = MutableLiveData<Either<HttpException, ArrayList<ImmobileVO>>>()

    fun getData(listType: ListType) {
        viewModelScope.launch {
            kotlin.runCatching {
                val result = async { repository.getData() }
                val filteredData = ArrayList<ImmobileVO>()
                (result.await() as Either.Right<List<ImmobileVO>>).value.forEach {
                    when (listType) {
                        ListType.VIVAREAL -> {
                            if (checkVivaImmobile(
                                    it.pricingInfos.businessType,
                                    it.pricingInfos.monthlyCondoFee,
                                    it.pricingInfos.price
                                )
                            ) {
                                filteredData.add(it)
                            }
                        }
                        ListType.ZAP -> {
                            if (checkZapImmobile(
                                    it.pricingInfos.businessType,
                                    it.pricingInfos.price,
                                    it.usableArea
                                ) || insideBoundBoxZap(
                                        it.address.geolocation.location.lat,
                                        it.address.geolocation.location.lon
                                    )
                            ) {
                                filteredData.add(it)
                            }
                        }
                    }
                }
                dataLiveData.postValue(Either.Right(filteredData))
            }
        }
    }

    private fun checkVivaImmobile(businessType: BusinessType, condoFee: Double, price: Double): Boolean {
        return businessType == BusinessType.RENTAL && calcCondoFee(price, condoFee) < PERCENT_MAX_CONDO_FEE
    }

    private fun checkZapImmobile(businessType: BusinessType, price: Double, usableArea: Double): Boolean {
        return businessType == BusinessType.SALE && calcAreaPrice(price, usableArea) > VALUE_MIN_AREA
    }

    private fun insideBoundBoxZap(lat: Double, lng: Double): Boolean {
        return lat > LAT_MIN && lat < LAT_MAX && lng > LNG_MIN && lng < LNG_MAX
    }

    private fun calcCondoFee(price: Double, condoFee: Double): Double {
        return condoFee / price
    }

    private fun calcAreaPrice(totalValue: Double, totalArea: Double): Double {
        return totalValue / totalArea
    }
}
