package com.example.zap.domain

import com.example.zap.core.Either
import com.example.zap.data.Location
import com.example.zap.data.services.Service
import com.example.zap.domain.UseCases.checkVivaImmobile
import com.example.zap.domain.UseCases.checkZapImmobile
import com.example.zap.presentation.enum.BusinessType
import com.example.zap.presentation.model.ImmobileVO
import retrofit2.HttpException

class Repository(private val service: Service) {

    suspend fun getVivaRealData() : Either<HttpException, ArrayList<ImmobileVO>>{
        return try {
            val result = service.getData()
            val filteredData = ArrayList<ImmobileVO>()
            result.forEach {
                it.address.geolocation.location.let { location ->
                    if (checkVivaImmobile(
                            businessType = BusinessType.valueOf(it.pricingInfos.businessType),
                            condoFee = it.pricingInfos.monthlyCondoFee,
                            price = it.pricingInfos.price,
                            lat = it.address.geolocation.location.lat,
                            lng = it.address.geolocation.location.lon
                        ) && checkGeopoint(location)
                    ) {
                        filteredData.add(it.toViewObject())
                    }
                }
            }
            Either.Right(filteredData)
        } catch (e: HttpException) {
            Either.Left(e)
        }
    }

    suspend fun getZapData() : Either<HttpException, ArrayList<ImmobileVO>>{
        return try {
            val result = service.getData()
            val filteredData = ArrayList<ImmobileVO>()
            result.forEach {
                it.address.geolocation.location.let { location ->
                    if (checkZapImmobile(
                            businessType = BusinessType.valueOf(it.pricingInfos.businessType),
                            price = it.pricingInfos.price,
                            usableArea = it.usableAreas
                        ) && checkGeopoint(location)
                    ) {
                        filteredData.add(it.toViewObject())
                    }
                }
            }
            Either.Right(filteredData)
        } catch (e: HttpException) {
            Either.Left(e)
        }
    }

    suspend fun getImmobileById(id: String): Either<HttpException, ImmobileVO?> {
        try {
            val result = service.getData()
            result.forEach {
                if (it.id == id) {
                    return Either.Right(it.toViewObject())
                }
            }
            return Either.Right(null)
        } catch (e: HttpException) {
            return Either.Left(e)
        }
    }

    private fun checkGeopoint(geopoint: Location): Boolean {
        return geopoint.lat != 0.0 && geopoint.lon != 0.0
    }
}
