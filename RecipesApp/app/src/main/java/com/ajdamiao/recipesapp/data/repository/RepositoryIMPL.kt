package com.ajdamiao.recipesapp.data.repository

import com.ajdamiao.recipesapp.BuildConfig
import com.ajdamiao.recipesapp.data.interceptor.TokenInterceptor
import com.ajdamiao.recipesapp.data.service.ApiService
import com.ajdamiao.recipesapp.model.Recipe
import com.ajdamiao.recipesapp.model.RecipeDetails
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RepositoryIMPL: Repository {
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
}