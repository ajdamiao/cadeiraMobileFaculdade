package com.ajdamiao.recipesapp.data.repository

import com.ajdamiao.recipesapp.data.service.ApiService
import com.ajdamiao.recipesapp.model.FavoriteRecipe
import com.ajdamiao.recipesapp.model.Recipe
import com.ajdamiao.recipesapp.model.RecipeDetails
import retrofit2.Response

interface Repository {

    fun makeRequest(): ApiService

    suspend fun getRecipesList() : ArrayList<Recipe>

    suspend fun getRecipeDetails(recipeId: String) : RecipeDetails

    suspend fun favoriteRecipe(recipe: FavoriteRecipe): Boolean
}