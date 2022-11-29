package com.ajdamiao.recipesapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajdamiao.recipesapp.MainActivity
import com.ajdamiao.recipesapp.R
import com.ajdamiao.recipesapp.adapter.FavoriteRecipesAdapter
import com.ajdamiao.recipesapp.adapter.RecipesAdapter
import com.ajdamiao.recipesapp.databinding.FragmentFavoriteRecipesBinding
import com.ajdamiao.recipesapp.model.FavoriteRecipe
import com.ajdamiao.recipesapp.viewmodel.FavoriteRecipesViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteRecipesFragment : Fragment(R.layout.fragment_favorite_recipes) {
    private lateinit var binding: FragmentFavoriteRecipesBinding
    private val recipesAdapter = FavoriteRecipesAdapter()
    private val favoriteRecipesViewModel: FavoriteRecipesViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentFavoriteRecipesBinding.bind(view)

        val main = activity as MainActivity
        main.changeToolbarTittle("Favorite Recipes")

        favoriteRecipesViewModel.getFavoriteRecipes(requireContext())
        observerRecipes()
    }

    private fun observerRecipes() {
        favoriteRecipesViewModel.favoriteRecipes.observe(viewLifecycleOwner) {
            when(it) {

                is ArrayList<FavoriteRecipe> -> { setupFavorites(it) }

                else -> {
                    binding.loading.visibility = View.GONE
                    binding.emptyState.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setupFavorites(recipesList: ArrayList<FavoriteRecipe>) {
        binding.loading.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
        recipesAdapter.addRecipe(recipesList)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = recipesAdapter
    }
}