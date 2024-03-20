package com.example.uasppb.repo

import com.example.uasppb.model.MealDetailResponse
import com.example.uasppb.service.ApiConfig
import retrofit2.Response

class MealDetailRepository {
    suspend fun getMealDetailById(idMeal:String):Response<MealDetailResponse> {
        return ApiConfig().getApiService().getMealDetailById(idMeal)
    }
}