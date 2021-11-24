package com.example.zap.core.koin

import com.example.zap.domain.Repository
import org.koin.dsl.module

val repositoryModule = module {
    single {
        Repository(
            service = get()
        )
    }
}
