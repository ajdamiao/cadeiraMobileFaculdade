package com.ajdamiao.recipesapp.data.usecase

import com.ajdamiao.recipesapp.data.repository.RepositoryIMPL
import com.ajdamiao.recipesapp.data.resource.Outcome
import com.ajdamiao.recipesapp.model.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException

class GetRecipeListUseCase(private val repository: RepositoryIMPL) {

    operator fun invoke(): Flow<Outcome<ArrayList<Recipe>>> = flow {
        try {
            emit(Outcome.Progress())
            val recipes = repository.getRecipesList()
            emit(Outcome.Success(recipes))
        } catch (e: HttpException) {
            emit(Outcome.Failure(e.message() ?: "An error ocurred"))
        }
    }
}