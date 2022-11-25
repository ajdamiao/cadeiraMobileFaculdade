package com.ajdamiao.recipesapp.data.usecase

import com.ajdamiao.recipesapp.data.repository.RepositoryIMPL
import com.ajdamiao.recipesapp.data.resource.Outcome
import com.ajdamiao.recipesapp.model.Recipe
import com.ajdamiao.recipesapp.model.RecipeDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class GetRecipeDetailsUseCase(private val repository: RepositoryIMPL) {

    operator fun invoke(recipeId: String): Flow<Outcome<RecipeDetails>> = flow {

        if(recipeId.isNotEmpty()) {
            try {
                emit(Outcome.Progress())
                val recipeDetails = repository.getRecipeDetails(recipeId)
                emit(Outcome.Success(recipeDetails))
            } catch (e: HttpException) {
                emit(Outcome.Failure(e.message() ?: "An error ocurred"))
            }
        } else {
            emit(Outcome.Failure("Recipe ID empty"))
        }
    }
}