package com.example.atividadefacul.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.atividadefacul.databinding.RviewTodosBinding
import com.example.atividadefacul.model.Todo

class TodosAdapter(private val todos: ArrayList<Todo>): RecyclerView.Adapter<TodosAdapter.TodosViewHolder>() {

    inner class TodosViewHolder(val binding: RviewTodosBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TodosAdapter.TodosViewHolder {
        val binding = RviewTodosBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TodosAdapter.TodosViewHolder, position: Int) {
        with(holder) {
            with(todos[position]) {

                binding.txtTodosTittle.text = title
                binding.txtTodosDescription.text = id.toString()
            }
        }
    }

    override fun getItemCount() = todos.size
}