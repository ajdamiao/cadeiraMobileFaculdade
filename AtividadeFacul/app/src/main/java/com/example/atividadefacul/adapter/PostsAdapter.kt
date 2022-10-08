package com.example.atividadefacul.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.atividadefacul.databinding.RviewPostBinding
import com.example.atividadefacul.model.Post

class PostsAdapter(private val posts: ArrayList<Post>): RecyclerView.Adapter<PostsAdapter.PostsViewHolder>() {

    inner class PostsViewHolder(val binding: RviewPostBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostsAdapter.PostsViewHolder {
        val binding = RviewPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostsAdapter.PostsViewHolder, position: Int) {
        with(holder) {
            with(posts[position]) {
                holder.binding.txtPostTittle.text = title
                holder.binding.txtPostDescription.text = body
            }
        }
    }

    override fun getItemCount() = posts.size
}