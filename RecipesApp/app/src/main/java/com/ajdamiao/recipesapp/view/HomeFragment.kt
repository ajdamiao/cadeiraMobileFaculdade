package com.ajdamiao.recipesapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajdamiao.recipesapp.R
import com.ajdamiao.recipesapp.adapter.RecipesAdapter
import com.ajdamiao.recipesapp.databinding.FragmentHomeBinding
import com.ajdamiao.recipesapp.model.Recipe
import com.ajdamiao.recipesapp.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private val recipesAdapter = RecipesAdapter()
    private val homeViewModel: HomeViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)

        homeViewModel.getRecipesList()

        setupObserver()
    }

    private fun setupObserver() {
        homeViewModel.recipeLiveData.observe(viewLifecycleOwner) { response ->
            when(response) {
                is ArrayList<*> -> { setupRecipeRecyclerView(response as ArrayList<Recipe>) }
            }
        }
    }

    private fun setupRecipeRecyclerView(recipesList: ArrayList<Recipe>) {
        recipesAdapter.addRecipe(recipesList)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = recipesAdapter

    }
}