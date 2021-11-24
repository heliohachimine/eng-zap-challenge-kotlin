package com.example.zap.domain

import com.example.zap.core.Either
import com.example.zap.data.Location
import com.example.zap.data.services.Service
import com.example.zap.presentation.model.ImmobileVO
import retrofit2.HttpException

class Repository(private val service: Service) {
    suspend fun getData(): Either<HttpException, List<ImmobileVO>> {
        return try {
            val result = service.getData()
            val filteredData = ArrayList<ImmobileVO>()
            result.forEach {
                it.address.geolocation.location.let { location ->
                    if (checkGeopoint(location)) {
                        filteredData.add(it.toViewObject())
                    }
                }
            }
            Either.Right(
                filteredData
            )
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
