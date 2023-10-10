package com.example.uasppb.repo

import android.content.Context
import com.example.uasppb.model.MealsResponse
import com.example.uasppb.service.ApiConfig
import retrofit2.Response

class MealRepository {
    suspend fun getMealByCat(strCategory:String): Response<MealsResponse> {
        return ApiConfig().getApiService().getMealByCat(strCategory)
    }
}