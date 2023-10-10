package com.example.uasppb.service

import com.example.uasppb.base.Constants
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    private lateinit var apiService: ApiService

    // Fungsi untuk mendapatkan instance ApiServices tanpa token
    fun getApiService(): ApiService {
        if (!::apiService.isInitialized) {
            // Konfigurasi logging interceptor untuk mencetak data permintaan dan respon ke logcat
            val mHttpLoggingInterceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

            // Membuat instance `OkHttpClient` dengan logging interceptor
            val mOkHttpClient = OkHttpClient
                .Builder()
                .addInterceptor(mHttpLoggingInterceptor)
                .build()

            // Membuat instance `Gson` dengan lenient parsing
            val gson = GsonBuilder().setLenient().create()

            // Membuat instance `Retrofit` dengan base url, `OkHttpClient`, dan `Gson` converter
            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            apiService = retrofit.create(ApiService::class.java)
        }
        return apiService
    }
}