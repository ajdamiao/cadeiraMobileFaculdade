package com.ajdamiao.recipesapp.data.service

import com.ajdamiao.recipesapp.model.Recipe
import com.ajdamiao.recipesapp.model.RecipeDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("findByIngredients?ingredients=meat")
    suspend fun getRecipesList(): ArrayList<Recipe>

    @GET("{id}/information")
    suspend fun getRecipeDetails(
        @Path("id") recipeId: String
    ): RecipeDetails

}