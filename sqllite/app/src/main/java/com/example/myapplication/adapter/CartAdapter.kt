package com.example.shoppingunhas.view.adapter

import CartDBHelper
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.RviewCartItemsBinding
import com.example.shoppingunhas.model.ProductCart

class CartAdapter(private val cartProducts: ArrayList<ProductCart>, private val view: View, private val context: Context) : RecyclerView.Adapter<CartAdapter.CartProductsViewHolder>()  {
    inner class CartProductsViewHolder(val binding: RviewCartItemsBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.CartProductsViewHolder {
        val binding = RviewCartItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartProductsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartProductsViewHolder, position: Int) {
        with(holder) {
            with(cartProducts[position]) {

                binding.run {
                    txtProductCartName.text = name
                    binding.btnRemoveItem.setOnClickListener {
                        removeItemFromCart(name)
                    }
                }
            }
        }
    }

    private fun removeItemFromCart(productName: String) {
        CartDBHelper(context).deleteItemFromCart(productName)
    }

    override fun getItemCount() = cartProducts.size
}