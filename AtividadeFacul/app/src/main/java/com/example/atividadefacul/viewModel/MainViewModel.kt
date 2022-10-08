package com.example.atividadefacul.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.atividadefacul.data.repository.AppRepositoryImpl
import com.example.atividadefacul.model.*
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
    private val _imagesMutableLiveData = MutableLiveData<ArrayList<Image>>()
    val imagesLiveData: LiveData<ArrayList<Image>> = _imagesMutableLiveData
    private val _todosMutableLiveData = MutableLiveData<ArrayList<Todo>>()
    val todosLiveData: LiveData<ArrayList<Todo>> = _todosMutableLiveData
    private val _usersMutableLiveData = MutableLiveData<ArrayList<User>>()
    val usersLiveData: LiveData<ArrayList<User>> = _usersMutableLiveData

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

    fun getImages() {
        viewModelScope.launch {
            val images = withContext(Dispatchers.Default) {
                repository.getImages()
            }

            _imagesMutableLiveData.value = images.body()
        }
    }


    fun getTodos() {
        viewModelScope.launch {
            val todos = withContext(Dispatchers.Default) {
                repository.getTodos()
            }

            _todosMutableLiveData.value = todos.body()
        }
    }

    fun getUsers() {
        viewModelScope.launch {
            val users = withContext(Dispatchers.Default) {
                repository.getUsers()
            }

            _usersMutableLiveData.value = users.body()
        }
    }
}