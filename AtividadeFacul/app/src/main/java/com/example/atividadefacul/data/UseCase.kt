package com.example.atividadefacul.data

import com.example.atividadefacul.model.*
import retrofit2.Response
import retrofit2.http.GET

interface UseCase {

    @GET("/posts")
    suspend fun getPosts(): Response<ArrayList<Post>>

    @GET("/comments")
    suspend fun getComments(): Response<ArrayList<Comments>>

    @GET("/albums")
    suspend fun getAlbums(): Response<ArrayList<Album>>

    @GET("/photos")
    suspend fun getImages(): Response<ArrayList<Image>>

    @GET("/todos")
    suspend fun getTodos(): Response<ArrayList<Todo>>

    @GET("/users")
    suspend fun getUsers(): Response<ArrayList<User>>
}