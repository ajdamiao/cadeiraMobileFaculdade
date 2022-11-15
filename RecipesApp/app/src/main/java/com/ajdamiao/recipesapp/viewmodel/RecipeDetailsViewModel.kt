package com.ajdamiao.recipesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajdamiao.recipesapp.data.repository.RepositoryIMPL
import com.ajdamiao.recipesapp.data.resource.Outcome
import com.ajdamiao.recipesapp.data.states.RecipeDetailsState
import com.ajdamiao.recipesapp.data.usecase.GetRecipeDetailsUseCase
import com.ajdamiao.recipesapp.model.Recipe
import com.ajdamiao.recipesapp.model.RecipeDetails
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class RecipeDetailsViewModel(private val getRecipeDetailsUseCase: GetRecipeDetailsUseCase): ViewModel() {
    private val _recipeDetails = MutableLiveData<RecipeDetailsState>()
    val recipeDetails: LiveData<RecipeDetailsState> = _recipeDetails

    fun getRecipeDetails(recipeId: String) {
        getRecipeDetailsUseCase(recipeId).onEach { result ->
            when(result) {

                is Outcome.Success -> { _recipeDetails.value = RecipeDetailsState(recipe = result.data) }

                is Outcome.Failure -> { _recipeDetails.value = RecipeDetailsState(error = result.message ?: "An unexpected error ocurred") }

                is Outcome.Progress -> { _recipeDetails.value = RecipeDetailsState(isLoading = true) }
            }
        }.launchIn(viewModelScope)
    }
}