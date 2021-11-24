package com.example.zap.core.koin

import com.example.zap.presentation.details.DetailsViewModel
import com.example.zap.presentation.main.MainViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainViewModel(repository = get())
    }
    viewModel {
        DetailsViewModel(repository = get())
    }
}
