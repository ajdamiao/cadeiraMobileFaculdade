package com.example.myapplication

import CartDBHelper
import DBContract
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityAddProductBinding
import com.example.shoppingunhas.model.ProductCart

class AddProductActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddProductBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnAddProduct.setOnClickListener {
            if(CartDBHelper(this).insertProductToCart(ProductCart(binding.inputProductName.text.toString()))) {
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}