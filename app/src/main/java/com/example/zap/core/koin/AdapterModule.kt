package com.example.zap.core.koin

import com.example.zap.presentation.details.adapter.ImageAdapter
import com.example.zap.presentation.main.adapter.ImmobileAdapter
import org.koin.dsl.module

val adapterModule = module {
    factory { ImmobileAdapter() }
    factory { ImageAdapter() }
}
