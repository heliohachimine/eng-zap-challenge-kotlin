package com.example.zap.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.zap.core.Either
import com.example.zap.domain.Repository
import com.example.zap.presentation.enum.BusinessType
import com.example.zap.presentation.enum.ListType
import com.example.zap.presentation.main.Utils.checkVivaImmobile
import com.example.zap.presentation.main.Utils.checkZapImmobile
import com.example.zap.presentation.main.Utils.insideBoundBoxZap
import com.example.zap.presentation.model.ImmobileVO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MainViewModel(private val repository: Repository) : ViewModel() {

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
                                    it.pricingInfos.price,
                                    it.address.geolocation.location.lat,
                                    it.address.geolocation.location.lon
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
}
