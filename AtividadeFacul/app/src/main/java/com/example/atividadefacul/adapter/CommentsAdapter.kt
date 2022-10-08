package com.example.atividadefacul.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.atividadefacul.databinding.RviewCommentsBinding
import com.example.atividadefacul.model.Comments

class CommentsAdapter(private val comments: ArrayList<Comments>): RecyclerView.Adapter<CommentsAdapter.CommentsViewHolder>() {
    inner class CommentsViewHolder(val binding: RviewCommentsBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CommentsAdapter.CommentsViewHolder {
        val binding = RviewCommentsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CommentsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CommentsAdapter.CommentsViewHolder, position: Int) {
        with(holder) {
            with(comments[position]) {
                holder.binding.txtCommentTittle.text = name
                holder.binding.txtCommentDescription.text = body
            }
        }
    }

    override fun getItemCount() = comments.size
}