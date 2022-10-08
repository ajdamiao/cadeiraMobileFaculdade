package com.example.atividadefacul.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.atividadefacul.databinding.RviewTodosBinding
import com.example.atividadefacul.databinding.RviewUserBinding
import com.example.atividadefacul.model.Todo
import com.example.atividadefacul.model.User

class UserAdapter(private val users: ArrayList<User>): RecyclerView.Adapter<UserAdapter.UsersViewHolder>() {

    inner class UsersViewHolder(val binding: RviewUserBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UserAdapter.UsersViewHolder {
        val binding = RviewUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UsersViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        with(holder) {
            with(users[position]) {

                binding.txtUserName.text = username
                binding.txtUserEmail.text = email

            }
        }
    }

    override fun getItemCount() = users.size
}