package com.example.atividadefacul.data.repository

import com.example.atividadefacul.data.UseCase
import com.example.atividadefacul.model.Album
import com.example.atividadefacul.model.Comments
import com.example.atividadefacul.model.Image
import com.example.atividadefacul.model.Post
import retrofit2.Response

interface AppRepository {

    fun makeRequest(): UseCase

    suspend fun getPosts(): Response<ArrayList<Post>>
    suspend fun getComments(): Response<ArrayList<Comments>>
    suspend fun getAlbums(): Response<ArrayList<Album>>
    suspend fun getImages(): Response<ArrayList<Image>>

}