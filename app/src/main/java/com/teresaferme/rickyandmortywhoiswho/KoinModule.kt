package com.teresaferme.rickyandmortywhoiswho

import com.teresaferme.rickyandmortywhoiswho.feature.main.MainViewModel
import com.teresaferme.rickyandmortywhoiswho.feature.main.RMMainInteractor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val MainModule = module {
    viewModel { MainViewModel() }
    single { RMMainInteractor() }
}