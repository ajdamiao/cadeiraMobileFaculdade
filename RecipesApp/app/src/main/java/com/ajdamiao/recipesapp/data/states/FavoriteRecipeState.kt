package com.ajdamiao.recipesapp.data.states

import com.ajdamiao.recipesapp.model.Recipe

data class FavoriteRecipeState (
    val isLoading: Boolean = false,
    val favoriteRecipe: Boolean = false,
    val error: String = ""
)