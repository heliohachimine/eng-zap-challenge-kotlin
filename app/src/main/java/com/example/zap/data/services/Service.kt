package com.example.zap.data.services

import com.example.zap.data.ResultData
import retrofit2.http.GET

interface Service {

    @GET("sources/source-1.json")
    suspend fun getData(): List<ResultData>
}
