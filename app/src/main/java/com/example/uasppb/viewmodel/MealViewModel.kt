package com.example.uasppb.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.uasppb.base.BaseResponse
import com.example.uasppb.model.MealsResponse
import com.example.uasppb.repo.MealRepository
import kotlinx.coroutines.launch

class MealViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = MealRepository()
    val mealResult: MutableLiveData<BaseResponse<MealsResponse>> =
        MutableLiveData()

    fun getMealByCat(strCategory: String) {
        mealResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val response = repo.getMealByCat(strCategory)
                if (response.isSuccessful) {
                    mealResult.value = BaseResponse.Success(response.body())
                } else {
                    mealResult.value =
                        BaseResponse.Error("Uppps, Terjadi kesalahan, Silahkan coba lagi")
                }
            } catch (e: Exception) {
                mealResult.value =
                    BaseResponse.Error(e.message)
            }
        }
    }
}