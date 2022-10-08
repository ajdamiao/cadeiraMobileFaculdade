package com.example.atividadefacul.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.atividadefacul.databinding.RviewImagesBinding
import com.example.atividadefacul.model.Image

class ImagesAdapter(private val images: ArrayList<Image>): RecyclerView.Adapter<ImagesAdapter.imagesViewHolder>() {
    inner class imagesViewHolder(val binding: RviewImagesBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImagesAdapter.imagesViewHolder {
        val binding = RviewImagesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return imagesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: imagesViewHolder, position: Int) {
        with(holder) {
            with(images[position]) {

                Glide.with(holder.itemView)
                    .load(url)
                    .into(holder.binding.image)
            }
        }
    }

    override fun getItemCount() = images.size

}