package com.ajdamiao.recipesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajdamiao.recipesapp.data.resource.Outcome
import com.ajdamiao.recipesapp.data.usecase.GetRecipeListUseCase
import com.ajdamiao.recipesapp.data.states.RecipeListState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeViewModel(private val getRecipeListUseCase: GetRecipeListUseCase): ViewModel() {
    private val _recipeList = MutableLiveData<RecipeListState>()
    val recipeLiveData: LiveData<RecipeListState> = _recipeList

    fun getRecipesList() {
        getRecipeListUseCase().onEach { result ->
            when(result) {
                is Outcome.Success -> {
                    _recipeList.value = RecipeListState(recipes = result.data ?: ArrayList())
                    println("SUCESSO")
                }

                is Outcome.Failure -> {
                    _recipeList.value = RecipeListState(error = result.message ?: "An unexpected error occurred")
                    println("ERROR")
                }

                is Outcome.Progress -> {
                    _recipeList.value = RecipeListState(isLoading = true)
                    println("PROGRESS")
                }
            }
        }.launchIn(viewModelScope)
    }
}