package com.ajdamiao.recipesapp.data.usecase

import com.ajdamiao.recipesapp.data.repository.RepositoryIMPL
import com.ajdamiao.recipesapp.data.resource.Outcome
import com.ajdamiao.recipesapp.model.FavoriteRecipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FavoriteRecipeUseCase(private val repository: RepositoryIMPL) {

    operator fun invoke(recipe: FavoriteRecipe): Flow<Outcome<Boolean>> = flow {
        if(recipe.title.isNotEmpty()) {
            try{
                println("aqui")
                emit(Outcome.Progress())
                val recipeFavorites = repository.favoriteRecipe(recipe)
                emit(Outcome.Success(recipeFavorites))
            } catch (exception: Exception) {
                emit(Outcome.Failure("Couldn't favorite recipe"))
            }
        } else {
            println("else0")
            emit(Outcome.Failure("Couldn't favorite recipe"))
        }
    }
}