package com.example.atividadefacul

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.atividadefacul.adapter.AlbumAdapter
import com.example.atividadefacul.adapter.CommentsAdapter
import com.example.atividadefacul.adapter.PostsAdapter
import com.example.atividadefacul.databinding.ActivityMainBinding
import com.example.atividadefacul.model.Album
import com.example.atividadefacul.model.Comments
import com.example.atividadefacul.model.Image
import com.example.atividadefacul.model.Post
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

            

        }
    }
}