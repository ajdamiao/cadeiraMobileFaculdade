package com.ajdamiao.recipesapp.di

import com.ajdamiao.recipesapp.data.repository.RepositoryIMPL
import com.ajdamiao.recipesapp.data.usecase.FavoriteRecipeUseCase
import com.ajdamiao.recipesapp.data.usecase.GetRecipeDetailsUseCase
import com.ajdamiao.recipesapp.data.usecase.GetRecipeListUseCase
import com.ajdamiao.recipesapp.viewmodel.FavoriteRecipesViewModel
import com.ajdamiao.recipesapp.viewmodel.HomeViewModel
import com.ajdamiao.recipesapp.viewmodel.RecipeDetailsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    single { RepositoryIMPL(context = androidContext()) }
    single { GetRecipeListUseCase(repository = get()) }
    single { GetRecipeDetailsUseCase(repository = get()) }
    single { FavoriteRecipeUseCase(repository = get()) }

    viewModel {
        HomeViewModel(
            getRecipeListUseCase = get()
        )
    }

    viewModel {
        RecipeDetailsViewModel(
            getRecipeDetailsUseCase = get(),
            favoriteRecipeUsecase = get()
        )
    }

    viewModel {
        FavoriteRecipesViewModel()
    }
}