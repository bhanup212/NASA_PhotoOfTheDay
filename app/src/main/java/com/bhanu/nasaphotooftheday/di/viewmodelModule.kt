package com.bhanu.nasaphotooftheday.di

import com.bhanu.nasaphotooftheday.ui.viewmodel.NasaViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 * Created by Bhanu Prakash Pasupula on 25,Jun-2020.
 * Email: pasupula1995@gmail.com
 */

val viewmodelModules = module {
    viewModel { NasaViewModel(get()) }
}