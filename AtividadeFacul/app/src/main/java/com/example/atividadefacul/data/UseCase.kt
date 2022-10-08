package com.example.atividadefacul.data

import com.example.atividadefacul.model.Album
import com.example.atividadefacul.model.Comments
import com.example.atividadefacul.model.Image
import com.example.atividadefacul.model.Post
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
}