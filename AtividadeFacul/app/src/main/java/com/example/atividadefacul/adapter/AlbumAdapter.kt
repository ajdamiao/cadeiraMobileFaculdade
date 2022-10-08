package com.example.atividadefacul.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.atividadefacul.databinding.RviewAlbumBinding
import com.example.atividadefacul.databinding.RviewCommentsBinding
import com.example.atividadefacul.model.Album
import com.example.atividadefacul.model.Comments

class AlbumAdapter(private val album: ArrayList<Album>): RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>()  {

    inner class AlbumViewHolder(val binding: RviewAlbumBinding): RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AlbumAdapter.AlbumViewHolder {
        val binding = RviewAlbumBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AlbumViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AlbumAdapter.AlbumViewHolder, position: Int) {
        with(holder) {
            with(album[position]) {

                holder.binding.txtAlbumTittle.text = title
                holder.binding.txtAlbumDescription.text = id.toString()

            }
        }
    }

    override fun getItemCount() = album.size
}