package com.ajdamiao.recipesapp.data.states

import com.ajdamiao.recipesapp.model.Recipe

data class RecipeListState (
    val isLoading: Boolean = false,
    val recipes: ArrayList<Recipe> = ArrayList(),
    val error: String = "",
)