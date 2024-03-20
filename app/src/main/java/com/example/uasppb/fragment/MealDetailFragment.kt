package com.example.uasppb.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.uasppb.R
import com.example.uasppb.base.BaseResponse
import com.example.uasppb.databinding.FragmentMealDetailBinding
import com.example.uasppb.model.MealDetailResponse
import com.example.uasppb.viewmodel.MealDetailViewModel
import com.squareup.picasso.Picasso

class MealDetailFragment : Fragment() {
    private var _binding: FragmentMealDetailBinding? = null
    private val binding get() = _binding!!

    private val mealDetailViewModel: MealDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMealDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
        back()
        binding.swipeRefresh.setOnRefreshListener {
            refresh()
        }
    }

    private fun getData() {
        val mealId = arguments?.getString("mealId")

        mealId?.let {
            mealDetailViewModel.getMealById(it)
            observeMealDetail()
        }
    }

    private fun observeMealDetail() {
        mealDetailViewModel.mealDetailResult.observe(viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Loading -> {
                    showLoading(true)
                }

                is BaseResponse.Success -> {
                    showLoading(false)
                    mealDetailSuccessProcess(it.data)
                }

                is BaseResponse.Error -> {
                    showLoading(false)
                    errorProcess("Terjadi Kesalahan")
                }

                else -> {
                    return@observe
                }
            }
        }
    }

    private fun mealDetailSuccessProcess(data: MealDetailResponse?) {
        if (data != null) {
            Picasso.get().load(data.data[0].strMealThumb).into(binding.ivMealThumb)
            binding.tvMealName.text = data.data[0].strMeal
            binding.tvLocation.text = data.data[0].strArea
            binding.tvTags.text = data.data[0].strTags

            val ingredientsList = mutableListOf<String?>()
            ingredientsList.add(data.data[0].strIngredient1)
            ingredientsList.add(data.data[0].strIngredient2)
            ingredientsList.add(data.data[0].strIngredient3)
            ingredientsList.add(data.data[0].strIngredient5)
            ingredientsList.add(data.data[0].strIngredient6)
            ingredientsList.add(data.data[0].strIngredient7)
            ingredientsList.add(data.data[0].strIngredient8)
            ingredientsList.add(data.data[0].strIngredient9)
            ingredientsList.add(data.data[0].strIngredient10)
            ingredientsList.add(data.data[0].strIngredient11)
            ingredientsList.add(data.data[0].strIngredient12)
            ingredientsList.add(data.data[0].strIngredient13)
            ingredientsList.add(data.data[0].strIngredient14)
            ingredientsList.add(data.data[0].strIngredient15)
            ingredientsList.add(data.data[0].strIngredient16.toString())
            ingredientsList.add(data.data[0].strIngredient17.toString())
            ingredientsList.add(data.data[0].strIngredient18.toString())
            ingredientsList.add(data.data[0].strIngredient19.toString())
            ingredientsList.add(data.data[0].strIngredient20.toString())

            val filteredIngredients = ingredientsList.filterNotNull()

            if (filteredIngredients.isNotEmpty()) {
                val ingredientsString = filteredIngredients.joinToString(separator = "\n")
                binding.tvMealIngredient.text = ingredientsString
            } else {
                binding.tvMealIngredient.text = ""
            }
        } else {
            binding.tvMealIngredient.text = ""
        }

        val measuresList = mutableListOf<String?>()
        measuresList.add(data!!.data[0].strMeasure1)
        measuresList.add(data.data[0].strMeasure2)
        measuresList.add(data.data[0].strMeasure3)
        measuresList.add(data.data[0].strMeasure4)
        measuresList.add(data.data[0].strMeasure5)
        measuresList.add(data.data[0].strMeasure6)
        measuresList.add(data.data[0].strMeasure7)
        measuresList.add(data.data[0].strMeasure8)
        measuresList.add(data.data[0].strMeasure9)
        measuresList.add(data.data[0].strMeasure10)
        measuresList.add(data.data[0].strMeasure11)
        measuresList.add(data.data[0].strMeasure12)
        measuresList.add(data.data[0].strMeasure13)
        measuresList.add(data.data[0].strMeasure14)
        measuresList.add(data.data[0].strMeasure15)
        measuresList.add(data.data[0].strMeasure16.toString())
        measuresList.add(data.data[0].strMeasure17.toString())
        measuresList.add(data.data[0].strMeasure18.toString())
        measuresList.add(data.data[0].strMeasure19.toString())
        measuresList.add(data.data[0].strMeasure20.toString())

        val filteredIngredients = measuresList.filterNotNull()

        if (filteredIngredients.isNotEmpty()) {
            val ingredientsString = filteredIngredients.joinToString(separator = "\n")
            binding.tvMealMeasures.text = ingredientsString
        } else {
            binding.tvMealMeasures.text = ""
        }

        binding.tvMealInstructions.text = data.data[0].strInstructions
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.pbProcess.visibility = View.VISIBLE
        } else {
            binding.pbProcess.visibility = View.GONE
        }
    }

    private fun errorProcess(msg: String?) {
        Toast.makeText(requireContext(), msg.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun back() {
        binding.btnBack.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.action_mealDetailFragment_to_homeFragment)
        }
    }

    private fun refresh() {
        binding.ivMealThumb.setImageDrawable(null)
        binding.tvMealName.text = ""
        binding.tvLocation.text = ""
        binding.tvTags.text = ""
        binding.tvMealIngredient.text = ""
        binding.tvMealMeasures.text = ""
        binding.tvMealInstructions.text = ""

        getData()

        binding.swipeRefresh.isRefreshing = false
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}