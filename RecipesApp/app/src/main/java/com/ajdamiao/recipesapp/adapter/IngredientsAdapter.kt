package com.ajdamiao.recipesapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ajdamiao.recipesapp.databinding.RviewIngredientsBinding
import com.ajdamiao.recipesapp.model.ExtendedIngredient

class IngredientsAdapter: RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder>() {
    inner class IngredientsViewHolder(val binding: RviewIngredientsBinding): RecyclerView.ViewHolder(binding.root)
    private val ingredients = ArrayList<ExtendedIngredient>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val binding = RviewIngredientsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IngredientsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        with(holder) {
            with(ingredients[position]) {
                binding.txtIngredientName.text = nameClean
            }
        }
    }

    override fun getItemCount() = ingredients.size

    fun addIngredient(newIngredients: ArrayList<ExtendedIngredient>) {
        newIngredients.forEach { t ->
            if(!ingredients.contains(t)) {
                ingredients.add(t)
            }
        }
        this.notifyDataSetChanged()
    }
}