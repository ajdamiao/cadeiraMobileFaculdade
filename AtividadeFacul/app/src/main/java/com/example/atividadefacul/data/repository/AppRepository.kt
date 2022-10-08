package com.example.atividadefacul.data.repository

import com.example.atividadefacul.data.UseCase
import com.example.atividadefacul.model.*
import retrofit2.Response

interface AppRepository {

    fun makeRequest(): UseCase

    suspend fun getPosts(): Response<ArrayList<Post>>
    suspend fun getComments(): Response<ArrayList<Comments>>
    suspend fun getAlbums(): Response<ArrayList<Album>>
    suspend fun getImages(): Response<ArrayList<Image>>
    suspend fun getTodos(): Response<ArrayList<Todo>>
    suspend fun getUsers(): Response<ArrayList<User>>

}