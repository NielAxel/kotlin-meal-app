package com.example.uasppb.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uasppb.adapter.CategoryAdapter
import com.example.uasppb.adapter.MealAdapter
import com.example.uasppb.base.BaseResponse
import com.example.uasppb.databinding.FragmentHomeBinding
import com.example.uasppb.model.CategoryItem
import com.example.uasppb.model.CategoryResponse
import com.example.uasppb.model.MealsResponse
import com.example.uasppb.viewmodel.CategoryViewModel
import com.example.uasppb.viewmodel.MealDetailViewModel
import com.example.uasppb.viewmodel.MealViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val catViewModel: CategoryViewModel by viewModels()
    private val mealViewModel: MealViewModel by viewModels()
    private val mealDetailModel: MealDetailViewModel by viewModels()

    private var selectedCategory: CategoryItem? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getDataCat()
        observeCategoryData()
        setupCategoryRV()

        binding.swipeRefresh.setOnRefreshListener {
            refresh()
        }
    }

    private fun setupSearchBar() {
        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(strName: String?): Boolean {
                // Dismiss keyboard when search submitted
                binding.searchBar.clearFocus()
                return true
            }

            override fun onQueryTextChange(strName: String?): Boolean {
                // Trigger search when text changes
                strName?.let { searchMeal(it) }
                return true
            }
        })
    }

    private fun searchMeal(strName: String) {
        mealDetailModel.getMealDetailByName(strName)
        mealDetailModel.mealDetailResult.observe(viewLifecycleOwner) { response ->
            when (response) {
                is BaseResponse.Loading -> {
                    // Show loading indicator
                }
                is BaseResponse.Success -> {
                    // Update RecyclerView with search results
                    val meals = response.data?.data!![0].let {
//                        mealListAdapter.setData(it)
                    }
                }
                is BaseResponse.Error -> {
                    errorProcess("Terjadi kesalahan")
                }

                else -> {
                    return@observe
                }
            }
        }
    }

    private fun changeCatName() {
        with(binding) {
            if (selectedCategory != null) {
                tvCategoryTitle.text = selectedCategory?.strCategory
            }
        }
    }

    private fun getDataCat() {
        catViewModel.getCategory()
    }

    private fun observeCategoryData() {
        catViewModel.categoryResult.observe(viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Loading -> {
                    showLoading(true)
                }

                is BaseResponse.Success -> {
                    showLoading(false)
                    catSuccessProcess(it.data)
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

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.pbProcess.visibility = View.VISIBLE
        } else {
            binding.pbProcess.visibility = View.GONE
        }
    }

    private fun catSuccessProcess(data: CategoryResponse?) {
        if (data != null) {
            val categoryAdapter = CategoryAdapter()
            binding.rvCategory.adapter = categoryAdapter

            categoryAdapter.itemClickListener.observe(viewLifecycleOwner) { categoryItem ->
                selectedCategory = categoryItem
                changeCatName()
                getMealData() // Memanggil fungsi untuk mendapatkan data meal berdasarkan kategori yang dipilih
            }

            data.categories.let { categoryList ->
                categoryAdapter.setData(categoryList)
            }
        }
    }

    private fun setupCategoryRV() {
        binding.rvCategory.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val categoryAdapter = CategoryAdapter()

        binding.rvCategory.adapter = categoryAdapter
    }

    private fun getMealData() {
        selectedCategory?.strCategory?.let { strCategory ->
            mealViewModel.getMealByCat(strCategory)
            observeMealData() // Memanggil fungsi untuk mengamati data meal
            setupMealRV() // Memanggil fungsi untuk setup RecyclerView meal
        }
    }

    private fun observeMealData() {
        mealViewModel.mealResult.observe(viewLifecycleOwner) {
            when (it) {
                is BaseResponse.Loading -> {
                    showLoading(true)
                }

                is BaseResponse.Success -> {
                    showLoading(false)
                    mealSuccessProcess(it.data)
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

    private fun mealSuccessProcess(data: MealsResponse?) {
        if (data != null) {
            val mealAdapter = MealAdapter()
            binding.rvMeal.adapter = mealAdapter
            mealAdapter.setData(data.meals)
        }
    }

    private fun setupMealRV() {
        val mealAdapter = MealAdapter()
        binding.rvMeal.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.rvMeal.adapter = mealAdapter
    }

    private fun errorProcess(msg: String?) {
        Toast.makeText(requireContext(), msg.toString(), Toast.LENGTH_SHORT).show()
    }

    private fun refresh() {
        selectedCategory = null
        getDataCat()
        binding.rvMeal.adapter = null
        binding.tvCategoryTitle.text = "Select Your Category"
        binding.swipeRefresh.isRefreshing = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
