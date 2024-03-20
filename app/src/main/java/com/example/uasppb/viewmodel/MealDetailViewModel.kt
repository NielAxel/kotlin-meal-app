package com.example.uasppb.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.uasppb.base.BaseResponse
import com.example.uasppb.model.MealDetailResponse
import com.example.uasppb.repo.MealDetailRepository
import kotlinx.coroutines.launch

class MealDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = MealDetailRepository()
    val mealDetailResult: MutableLiveData<BaseResponse<MealDetailResponse>> =
        MutableLiveData()

    fun getMealById(idMeal: String) {
        mealDetailResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val response = repo.getMealDetailById(idMeal)
                if (response.isSuccessful) {
                    mealDetailResult.value = BaseResponse.Success(response.body())
                } else {
                    mealDetailResult.value =
                        BaseResponse.Error("Uppps, Terjadi kesalahan, Silahkan coba lagi")
                }
            } catch (e: Exception) {
                mealDetailResult.value =
                    BaseResponse.Error(e.message)
            }
        }
    }

    fun getMealDetailByName(strName: String) {
        mealDetailResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val response = repo.getMealDetailById(strName)
                if (response.isSuccessful) {
                    mealDetailResult.value = BaseResponse.Success(response.body())
                } else {
                    mealDetailResult.value =
                        BaseResponse.Error("Uppps, Terjadi kesalahan, Silahkan coba lagi")
                }
            } catch (e: Exception) {
                mealDetailResult.value =
                    BaseResponse.Error(e.message)
            }
        }
    }

}