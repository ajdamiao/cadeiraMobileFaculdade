package com.ajdamiao.recipesapp.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ajdamiao.recipesapp.R
import com.ajdamiao.recipesapp.databinding.RviewRecipeItemBinding
import com.ajdamiao.recipesapp.model.FavoriteRecipe
import com.ajdamiao.recipesapp.model.Recipe
import com.bumptech.glide.Glide

class FavoriteRecipesAdapter : RecyclerView.Adapter<FavoriteRecipesAdapter.RecipesViewHolder>() {
    private val recipesList = ArrayList<FavoriteRecipe>()
    inner class RecipesViewHolder(val binding: RviewRecipeItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavoriteRecipesAdapter.RecipesViewHolder {
        val binding = RviewRecipeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipesViewHolder(binding)
    }

    override fun getItemCount() = recipesList.size

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
                }
            }
        }
    }

    fun addRecipe(newRecipeList: ArrayList<FavoriteRecipe>) {
        newRecipeList.forEach { t ->
            if(!recipesList.contains(t)) {
                recipesList.add(t)
            }
        }
    }
}