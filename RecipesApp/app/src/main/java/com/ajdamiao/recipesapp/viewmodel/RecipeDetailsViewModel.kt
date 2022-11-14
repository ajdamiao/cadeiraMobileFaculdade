package com.ajdamiao.recipesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajdamiao.recipesapp.data.repository.RepositoryIMPL
import com.ajdamiao.recipesapp.model.Recipe
import com.ajdamiao.recipesapp.model.RecipeDetails
import kotlinx.coroutines.launch

class RecipeDetailsViewModel(private val repository: RepositoryIMPL): ViewModel() {
    private val _recipeDetails = MutableLiveData<RecipeDetails>()
    val recipeDetails: LiveData<RecipeDetails> = _recipeDetails

    fun getRecipeDetails(recipeId: String) {
        viewModelScope.launch {
            val response = repository.getRecipeDetails(recipeId)

            if(response.isSuccessful) {

                _recipeDetails.value = response.body()

            }
        }
    }
}