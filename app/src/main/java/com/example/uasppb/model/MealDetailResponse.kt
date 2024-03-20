package com.example.uasppb.model

import com.google.gson.annotations.SerializedName

data class MealDetailResponse(

	@field:SerializedName("meals")
	val data: List<MealDetail>
)

data class MealDetail(

	@field:SerializedName("strImageSource")
	val strImageSource: Any,

	@field:SerializedName("strIngredient10")
	val strIngredient10: String,

	@field:SerializedName("strIngredient12")
	val strIngredient12: String,

	@field:SerializedName("strIngredient11")
	val strIngredient11: String,

	@field:SerializedName("strIngredient14")
	val strIngredient14: String,

	@field:SerializedName("strCategory")
	val strCategory: String,

	@field:SerializedName("strIngredient13")
	val strIngredient13: String,

	@field:SerializedName("strIngredient16")
	val strIngredient16: Any,

	@field:SerializedName("strIngredient15")
	val strIngredient15: String,

	@field:SerializedName("strIngredient18")
	val strIngredient18: Any,

	@field:SerializedName("strIngredient17")
	val strIngredient17: Any,

	@field:SerializedName("strArea")
	val strArea: String,

	@field:SerializedName("strCreativeCommonsConfirmed")
	val strCreativeCommonsConfirmed: Any,

	@field:SerializedName("strIngredient19")
	val strIngredient19: Any,

	@field:SerializedName("strTags")
	val strTags: String,

	@field:SerializedName("idMeal")
	val idMeal: String,

	@field:SerializedName("strInstructions")
	val strInstructions: String,

	@field:SerializedName("strIngredient1")
	val strIngredient1: String,

	@field:SerializedName("strIngredient3")
	val strIngredient3: String,

	@field:SerializedName("strIngredient2")
	val strIngredient2: String,

	@field:SerializedName("strIngredient20")
	val strIngredient20: Any,

	@field:SerializedName("strIngredient5")
	val strIngredient5: String,

	@field:SerializedName("strIngredient4")
	val strIngredient4: String,

	@field:SerializedName("strIngredient7")
	val strIngredient7: String,

	@field:SerializedName("strIngredient6")
	val strIngredient6: String,

	@field:SerializedName("strIngredient9")
	val strIngredient9: String,

	@field:SerializedName("strIngredient8")
	val strIngredient8: String,

	@field:SerializedName("strMealThumb")
	val strMealThumb: String,

	@field:SerializedName("strMeasure20")
	val strMeasure20: Any,

	@field:SerializedName("strYoutube")
	val strYoutube: String,

	@field:SerializedName("strMeal")
	val strMeal: String,

	@field:SerializedName("strMeasure12")
	val strMeasure12: String,

	@field:SerializedName("strMeasure13")
	val strMeasure13: String,

	@field:SerializedName("strMeasure10")
	val strMeasure10: String,

	@field:SerializedName("strMeasure11")
	val strMeasure11: String,

	@field:SerializedName("dateModified")
	val dateModified: Any,

	@field:SerializedName("strDrinkAlternate")
	val strDrinkAlternate: Any,

	@field:SerializedName("strSource")
	val strSource: Any,

	@field:SerializedName("strMeasure9")
	val strMeasure9: String,

	@field:SerializedName("strMeasure7")
	val strMeasure7: String,

	@field:SerializedName("strMeasure8")
	val strMeasure8: String,

	@field:SerializedName("strMeasure5")
	val strMeasure5: String,

	@field:SerializedName("strMeasure6")
	val strMeasure6: String,

	@field:SerializedName("strMeasure3")
	val strMeasure3: String,

	@field:SerializedName("strMeasure4")
	val strMeasure4: String,

	@field:SerializedName("strMeasure1")
	val strMeasure1: String,

	@field:SerializedName("strMeasure18")
	val strMeasure18: Any,

	@field:SerializedName("strMeasure2")
	val strMeasure2: String,

	@field:SerializedName("strMeasure19")
	val strMeasure19: Any,

	@field:SerializedName("strMeasure16")
	val strMeasure16: Any,

	@field:SerializedName("strMeasure17")
	val strMeasure17: Any,

	@field:SerializedName("strMeasure14")
	val strMeasure14: String,

	@field:SerializedName("strMeasure15")
	val strMeasure15: String
)
