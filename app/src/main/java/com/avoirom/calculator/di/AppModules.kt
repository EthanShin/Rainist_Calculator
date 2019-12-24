package com.avoirom.calculator.di

import com.avoirom.calculator.ui.main.MainViewModel
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel

val viewModelModule = module {
    viewModel {
        MainViewModel()
    }
}

val appModule = listOf(viewModelModule)