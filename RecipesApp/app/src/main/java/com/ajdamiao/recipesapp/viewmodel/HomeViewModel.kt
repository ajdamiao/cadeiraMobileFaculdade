package com.ajdamiao.recipesapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajdamiao.recipesapp.data.repository.RepositoryIMPL
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: RepositoryIMPL): ViewModel() {
    private val _recipeList = MutableLiveData<Any>()
    val recipeLiveData: LiveData<Any> = _recipeList

    fun getRecipesList() {
        viewModelScope.launch {
            val response = repository.getRecipesList()
            when {
                response.isSuccessful -> { _recipeList.value = response.body() }

                else -> { _recipeList.value = response.errorBody() }
            }
        }
    }
}