package com.example.uasppb.service

import com.example.uasppb.base.Constants
import com.example.uasppb.model.CategoryResponse
import com.example.uasppb.model.MealDetailResponse
import com.example.uasppb.model.MealsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(Constants.CATEGORIES_URL)
    suspend fun getCategory(): Response<CategoryResponse>

    @GET(Constants.MEAL_BY_CAT_URL)
    suspend fun getMealByCat(@Query("c") strCategory: String): Response<MealsResponse>

    @GET(Constants.MEAL_DETAIL_URL)
    suspend fun getMealDetailById(@Query("i") idMeal: String): Response<MealDetailResponse>

    @GET(Constants.SEARCH_MEAL_URL)
    suspend fun getMealDetailByName(@Query("s") strName: String): Response<MealDetailResponse>
}