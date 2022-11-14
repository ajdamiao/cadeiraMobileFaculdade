package com.ajdamiao.recipesapp.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ajdamiao.recipesapp.R
import com.ajdamiao.recipesapp.databinding.RviewRecipeItemBinding
import com.ajdamiao.recipesapp.model.Recipe
import com.bumptech.glide.Glide

class RecipesAdapter : RecyclerView.Adapter<RecipesAdapter.RecipesViewHolder>() {
    private val recipesList = ArrayList<Recipe>()
    inner class RecipesViewHolder(val binding: RviewRecipeItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val binding = RviewRecipeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        with(holder) {
            with(recipesList[position]) {

                itemView.setOnClickListener {
                    val bundle = Bundle()
                    bundle.putString("recipeId", id.toString())
                    Navigation.findNavController(itemView).navigate(R.id.recipeDetailsFragment, bundle)
                }

                binding.run{
                    Glide.with(holder.itemView)
                        .load(image)
                        .into(imgRecipe)

                    txtRecipeName.text = title
                    txtRecipeDescription.text = likes.toString()
                }

            }
        }
    }

    override fun getItemCount() = recipesList.size

    fun addRecipe(newRecipeList: ArrayList<Recipe>) {
        newRecipeList.forEach { t ->
            if(!recipesList.contains(t)) {
                recipesList.add(t)
            }
        }
    }
}