package com.ajdamiao.recipesapp.view

import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.ajdamiao.recipesapp.R
import com.ajdamiao.recipesapp.adapter.IngredientsAdapter
import com.ajdamiao.recipesapp.databinding.FragmentRecipeDetailsBinding
import com.ajdamiao.recipesapp.model.RecipeDetails
import com.ajdamiao.recipesapp.viewmodel.RecipeDetailsViewModel
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel


class RecipeDetailsFragment : Fragment(R.layout.fragment_recipe_details) {
    private lateinit var binding: FragmentRecipeDetailsBinding
    private val ingredientsAdapter = IngredientsAdapter()
    private val recipeDetailsViewModel: RecipeDetailsViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRecipeDetailsBinding.bind(view)

        val id = requireArguments().getString("recipeId")

        if(id!=null) {
            recipeDetailsViewModel.getRecipeDetails(id)
        }

        recipeDetailsViewModel.recipeDetails.observe(viewLifecycleOwner){ response ->
            when {
                response.isLoading -> {
                    println("Carregnado")
                }

                response.recipe?.imageType?.isNotEmpty() == true -> {
                    setupDetails(response.recipe)
                }

                response.error.isNotEmpty() -> {
                    println("ERROR")
                }
            }
        }
    }

    private fun setupDetails(response: RecipeDetails) {
        binding.run {
            Glide.with(requireView())
                .load(response.image)
                .into(imgRecipeDetails)

            txtRecipeTittle.text = response.title
            txtServings.text = response.servings.toString()
            txtTime.text = response.readyInMinutes.toString()
            txtInstructions.text = Html.fromHtml(response.instructions).toString()

            ingredientsAdapter.addIngredient(response.extendedIngredients)
            binding.recyclerView2.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.recyclerView2.adapter = ingredientsAdapter
        }
    }
}