package com.ajdamiao.recipesapp.data.repository

import android.content.Context
import com.ajdamiao.recipesapp.data.sqllite.DBHelper
import com.ajdamiao.recipesapp.BuildConfig
import com.ajdamiao.recipesapp.data.interceptor.TokenInterceptor
import com.ajdamiao.recipesapp.data.service.ApiService
import com.ajdamiao.recipesapp.model.FavoriteRecipe
import com.ajdamiao.recipesapp.model.Recipe
import com.ajdamiao.recipesapp.model.RecipeDetails
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RepositoryIMPL(private val context: Context): Repository {
    private val BASEURL= BuildConfig.Base_URL

    override fun makeRequest(): ApiService {
        val client = OkHttpClient.Builder()
            .addInterceptor(TokenInterceptor())
            .build()

        return Retrofit
            .Builder()
            .baseUrl(BASEURL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    override suspend fun getRecipesList(): ArrayList<Recipe> {
        return makeRequest().getRecipesList()
    }

    override suspend fun getRecipeDetails(recipeId: String): RecipeDetails {
        return makeRequest().getRecipeDetails(recipeId)
    }

    override suspend fun favoriteRecipe(recipe: FavoriteRecipe): Boolean {
        return DBHelper(context = context).favoriteRecipe(recipe)
    }
}