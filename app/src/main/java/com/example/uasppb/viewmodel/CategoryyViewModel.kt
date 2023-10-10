package com.example.uasppb.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.uasppb.base.BaseResponse
import com.example.uasppb.model.CategoryResponse
import com.example.uasppb.repo.CategoryRepository
import kotlinx.coroutines.launch

class CategoryViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = CategoryRepository()
    val categoryResult: MutableLiveData<BaseResponse<CategoryResponse>> =
        MutableLiveData()

    fun getCategory() {
        categoryResult.value = BaseResponse.Loading()
        viewModelScope.launch {
            try {
                val response = repo.getCategory()
                if (response.isSuccessful) {
                    categoryResult.value = BaseResponse.Success(response.body())
                } else {
                    categoryResult.value =
                        BaseResponse.Error("Uppps, Terjadi kesalahan, Silahkan coba lagi")
                }
            } catch (e: Exception) {
                categoryResult.value =
                    BaseResponse.Error(e.message)
            }
        }
    }
}