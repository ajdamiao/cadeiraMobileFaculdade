package com.ajdamiao.recipesapp.data.states

import com.ajdamiao.recipesapp.model.RecipeDetails

data class RecipeDetailsState (
    val isLoading: Boolean = false,
    val recipe: RecipeDetails? = null,
    val error: String = ""
)