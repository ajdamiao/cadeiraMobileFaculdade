package com.ajdamiao.recipesapp.view

import android.content.Intent
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.ajdamiao.recipesapp.MainActivity
import com.ajdamiao.recipesapp.R
import com.ajdamiao.recipesapp.adapter.IngredientsAdapter
import com.ajdamiao.recipesapp.databinding.FragmentRecipeDetailsBinding
import com.ajdamiao.recipesapp.model.FavoriteRecipe
import com.ajdamiao.recipesapp.model.Recipe
import com.ajdamiao.recipesapp.model.RecipeDetails
import com.ajdamiao.recipesapp.viewmodel.RecipeDetailsViewModel
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel


class RecipeDetailsFragment : Fragment(R.layout.fragment_recipe_details) {
    private lateinit var binding: FragmentRecipeDetailsBinding
    private val ingredientsAdapter = IngredientsAdapter()
    private val recipeDetailsViewModel: RecipeDetailsViewModel by viewModel()
    private lateinit var recipe: FavoriteRecipe
    private lateinit var recipeShare: RecipeDetails

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentRecipeDetailsBinding.bind(view)

        val id = requireArguments().getString("recipeId")

        val main = activity as MainActivity
        main.changeToolbarTittle("Recipe Details")

        if(id != null) {
            recipeDetailsViewModel.getRecipeDetails(id)
        }

        setupObservers()
        setupComponents()
    }

    private fun setupObservers() {
        recipeDetailsViewModel.recipeDetails.observe(viewLifecycleOwner){ response ->
            when {
                response.recipe?.imageType?.isNotEmpty() == true -> {
                    setupDetails(response.recipe)
                }

                response.isLoading -> {
                    binding.recipeHeader.visibility = View.GONE
                    binding.recipeDetailsLayout.visibility = View.GONE
                    binding.loading.visibility = View.VISIBLE

                }

                response.error.isNotEmpty() -> {
                    println("ERROR")
                }
            }
        }

        recipeDetailsViewModel.favoriteRecipe.observe(viewLifecycleOwner) { response ->
            when {
                response.favoriteRecipe -> { Toast.makeText(requireContext(), "Receita salva aos favoritos", Toast.LENGTH_SHORT).show() }

                response.error.isNotEmpty() -> { Toast.makeText(requireContext(), response.error, Toast.LENGTH_SHORT).show() }
            }
        }
    }

    private fun setupComponents() {
        binding.btnFavorite.setOnClickListener {
            recipeDetailsViewModel.setRecipeAsFavorite(recipe = recipe)
        }

        binding.btnShare.setOnClickListener {
            shareFunction()
        }
    }

    private fun setupDetails(response: RecipeDetails) {
        binding.run {
            recipeShare = response

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


            binding.recipeHeader.visibility = View.VISIBLE
            binding.recipeDetailsLayout.visibility = View.VISIBLE
            binding.loading.visibility = View.GONE

            recipe = FavoriteRecipe(response.id.toString(), response.title, response.summary, response.image)
        }
    }

    private fun shareFunction() {
        val sharingIntent = Intent(Intent.ACTION_SEND)
        val ingredients = ArrayList<String>()
        for(i in 0 until recipeShare.extendedIngredients.size) {
            ingredients.add(recipeShare.extendedIngredients[i].name)
        }

        sharingIntent.putExtra(Intent.EXTRA_TEXT, "${recipeShare.title} \n ================= \n Ingredients: \n ${ingredients}  \n ================= \n How to prepare: \n ${Html.fromHtml(recipeShare.instructions)}")
        sharingIntent.type = "text/plain"
        startActivity(Intent.createChooser(sharingIntent, "Compartilhar Evento"))
    }}