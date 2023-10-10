package com.example.uasppb.repo

import com.example.uasppb.model.CategoryResponse
import com.example.uasppb.service.ApiConfig
import retrofit2.Response

class CategoryRepository {
    suspend fun getCategory(): Response<CategoryResponse> {
        return ApiConfig().getApiService().getCategory()
    }
}