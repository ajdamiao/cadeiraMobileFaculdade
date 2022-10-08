package com.example.atividadefacul

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.atividadefacul.adapter.*
import com.example.atividadefacul.databinding.ActivityMainBinding
import com.example.atividadefacul.model.*
import com.example.atividadefacul.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.getPosts()
        mainViewModel.getComments()
        mainViewModel.getAlbums()
        mainViewModel.getImages()
        mainViewModel.getTodos()
        mainViewModel.getUsers()

        observers()
    }

    private fun observers() {

        mainViewModel.postsLiveData.observe(this) {
            when(it) {
                is ArrayList<Post> -> setupPosts(it)
            }
        }

        mainViewModel.commentsLiveData.observe(this){
            when(it) {
                is ArrayList<Comments> -> setupComments(it)
            }
        }

        mainViewModel.albumLiveData.observe(this){
            when(it) {
                is ArrayList<Album> -> setupAlbums(it)
            }
        }

        mainViewModel.imagesLiveData.observe(this){
            when(it) {
                is ArrayList<Image> -> setupImages(it)
            }
        }

        mainViewModel.todosLiveData.observe(this){
            when(it) {
                is ArrayList<Todo> -> setupTodos(it)
            }
        }

        mainViewModel.usersLiveData.observe(this){
            when(it) {
                is ArrayList<User> -> setupUsers(it)
            }
        }
    }

    private fun setupUsers(it: java.util.ArrayList<User>) {
        binding.run {
            rviewAlbum.layoutManager = LinearLayoutManager(MainActivity())
            rviewAlbum.adapter = UserAdapter(it)
        }
    }

    private fun setupAlbums(it: java.util.ArrayList<Album>) {
        binding.run {
            rviewAlbum.layoutManager = LinearLayoutManager(MainActivity())
            rviewAlbum.adapter = AlbumAdapter(it)
        }
    }

    private fun setupComments(it: ArrayList<Comments>) {
        binding.run {
            rviewComments.layoutManager = LinearLayoutManager(MainActivity())
            rviewComments.adapter = CommentsAdapter(it)
        }
    }

    private fun setupPosts(posts: ArrayList<Post>) {
        binding.run {
            rviewPosts.layoutManager = LinearLayoutManager(MainActivity())
            rviewPosts.adapter = PostsAdapter(posts)
        }
    }

    private fun setupImages(images: ArrayList<Image>) {
        binding.run {
            rviewImages.setLayoutManager(LinearLayoutManager(MainActivity(), LinearLayoutManager.HORIZONTAL, true))
            rviewImages.adapter = ImagesAdapter(images)
        }
    }

    private fun setupTodos(todos: ArrayList<Todo>) {
        binding.run {
            rviewPosts.layoutManager = LinearLayoutManager(MainActivity())
            rviewImages.adapter = TodosAdapter(todos)
        }
    }
}