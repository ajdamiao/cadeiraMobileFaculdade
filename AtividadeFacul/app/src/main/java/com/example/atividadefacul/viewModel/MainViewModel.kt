package com.example.atividadefacul.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atividadefacul.data.repository.AppRepositoryImpl
import com.example.atividadefacul.model.Album
import com.example.atividadefacul.model.Comments
import com.example.atividadefacul.model.Post
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel() {
    private val repository = AppRepositoryImpl()

    private val _postsMutableLiveData = MutableLiveData<ArrayList<Post>>()
    val postsLiveData: LiveData<ArrayList<Post>> = _postsMutableLiveData
    private val _commentsMutableLiveData = MutableLiveData<ArrayList<Comments>>()
    val commentsLiveData: LiveData<ArrayList<Comments>> = _commentsMutableLiveData
    private val _albumMutableLiveData = MutableLiveData<ArrayList<Album>>()
    val albumLiveData: LiveData<ArrayList<Album>> = _albumMutableLiveData

    fun getPosts() {
        viewModelScope.launch {
            val posts = withContext(Dispatchers.Default) {
                repository.getPosts()
            }

            _postsMutableLiveData.value = posts.body()
        }
    }

    fun getComments() {
        viewModelScope.launch {
            val comments = withContext(Dispatchers.Default) {
                repository.getComments()
            }

            _commentsMutableLiveData.value = comments.body()
        }
    }

    fun getAlbums() {
        viewModelScope.launch {
            val albums = withContext(Dispatchers.Default) {
                repository.getAlbums()
            }

            _albumMutableLiveData.value = albums.body()
        }
    }

}