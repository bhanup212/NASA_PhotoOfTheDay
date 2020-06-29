package com.bhanu.nasaphotooftheday.network

import com.bhanu.nasaphotooftheday.model.Apod
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit


/**
 * Created by Bhanu Prakash Pasupula on 25,Jun-2020.
 * Email: pasupula1995@gmail.com
 */
interface ApiClient {
    @GET("/planetary/apod")
    suspend fun getClubs(
        @Query("api_key") apiKey:String= API_KEY,
        @Query("date") date:String
    ): Apod


    companion object {

        private const val API_KEY = "z74WZyeoONNW9XFeovLXpK9hctYiF1lAc2opmohb"

        private val gson: Gson = GsonBuilder()
            .serializeNulls()
            .setPrettyPrinting()
            .setLenient()
            .create()

        private val okHttpAuthClient: OkHttpClient = OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(2, TimeUnit.MINUTES)
            .writeTimeout(3, TimeUnit.MINUTES)
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()

        val apiClient = Retrofit.Builder()
            .baseUrl("https://api.nasa.gov/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpAuthClient)
            .build()
    }
}