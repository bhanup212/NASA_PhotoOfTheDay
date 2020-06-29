package com.bhanu.nasaphotooftheday.di

import com.bhanu.nasaphotooftheday.network.ApiClient
import org.koin.dsl.module


/**
 * Created by Bhanu Prakash Pasupula on 25,Jun-2020.
 * Email: pasupula1995@gmail.com
 */


val networkModule = module {

    fun provideRetrofitApi(): ApiClient = ApiClient.apiClient.create(ApiClient::class.java)

    single { provideRetrofitApi() }
}