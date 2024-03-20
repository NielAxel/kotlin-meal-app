package com.example.uasppb.repo

import com.example.uasppb.model.MealDetailResponse
import com.example.uasppb.service.ApiConfig
import retrofit2.Response

class SearchRepository {
    suspend fun getMealDetailByName(strName:String): Response<MealDetailResponse> {
        return ApiConfig().getApiService().getMealDetailByName(strName)
    }
}