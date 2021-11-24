package com.example.zap.core.koin

import com.example.zap.BuildConfig
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module(override = true) {
    single {
        HttpLoggingInterceptor().apply { this.level = HttpLoggingInterceptor.Level.BODY }
    }

    single(named("INTERCEPTOR")) {
        object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): Response {
                val original: Request = chain.request()
                val originalHttpUrl: HttpUrl = original.url

                val url: HttpUrl = originalHttpUrl.newBuilder()
                    .build()

                val requestBuilder = original.newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .url(url)

                val request = requestBuilder.build()
                return chain.proceed(request)
            }
        }
    }

    single {
        OkHttpClient
            .Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .addInterceptor(get<Interceptor>(named("INTERCEPTOR")))
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }
}
