package com.example.atividadefacul.data.repository

import com.example.atividadefacul.data.UseCase
import com.example.atividadefacul.model.Album
import com.example.atividadefacul.model.Comments
import com.example.atividadefacul.model.Image
import com.example.atividadefacul.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class AppRepositoryImpl: AppRepository {
    private var baseUrl = "https://jsonplaceholder.typicode.com/"
    private val timeout : Long = 60

    override fun makeRequest(): UseCase {

        val client = OkHttpClient.Builder()
            .readTimeout(timeout, TimeUnit.SECONDS)
            .build()

        return Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UseCase::class.java)
    }

    override suspend fun getPosts(): Response<ArrayList<Post>> {
        return withContext(Dispatchers.IO) {
            makeRequest().getPosts()
        }
    }

    override suspend fun getComments(): Response<ArrayList<Comments>> {
        return withContext(Dispatchers.IO) {
            makeRequest().getComments()
        }
    }

    override suspend fun getAlbums(): Response<ArrayList<Album>> {
        return withContext(Dispatchers.IO) {
            makeRequest().getAlbums()
        }
    }

    override suspend fun getImages(): Response<ArrayList<Image>> {
        return withContext(Dispatchers.IO) {
            makeRequest().getImages()
        }
    }
}