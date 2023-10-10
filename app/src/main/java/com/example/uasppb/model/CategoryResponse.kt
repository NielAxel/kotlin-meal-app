package com.example.uasppb.model

import com.google.gson.annotations.SerializedName

data class CategoryResponse(
	@SerializedName("categories")
	val categories: List<CategoryItem>
)

data class CategoryItem(
	@SerializedName("strCategory")
	val strCategory: String?,

	@field:SerializedName("strCategoryDescription")
	val strCategoryDescription: String,

	@field:SerializedName("idCategory")
	val idCategory: String,

	@field:SerializedName("strCategoryThumb")
	val strCategoryThumb: String
)