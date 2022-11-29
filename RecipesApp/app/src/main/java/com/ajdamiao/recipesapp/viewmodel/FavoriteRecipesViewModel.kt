package com.ajdamiao.recipesapp.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajdamiao.recipesapp.data.sqllite.DBHelper
import com.ajdamiao.recipesapp.model.FavoriteRecipe
import kotlinx.coroutines.launch

class FavoriteRecipesViewModel: ViewModel() {
    private val _favoriteRecipes = MutableLiveData<ArrayList<FavoriteRecipe>>()
    val favoriteRecipes: LiveData<ArrayList<FavoriteRecipe>> = _favoriteRecipes

    fun getFavoriteRecipes(context: Context) {
        viewModelScope.launch {
            _favoriteRecipes.value = DBHelper(context).getFavoriteRecipes()
        }
    }
}