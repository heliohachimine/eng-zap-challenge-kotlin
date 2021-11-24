package com.example.zap.core.koin

import com.example.zap.data.services.Service
import org.koin.dsl.module
import retrofit2.Retrofit

val serviceModule = module {
    single {
        (get() as Retrofit).create(Service::class.java)
    }
}
