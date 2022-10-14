package com.example.myapplication

import CartDBHelper
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.shoppingunhas.view.adapter.CartAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupCart()
        initSharedPref()
        getFromShared()

        binding.btnGoToAddProduct.setOnClickListener {
            val intent = Intent(this,AddProductActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.button.setOnClickListener {
            saveOnSharedPref(binding.inputName.text.toString())
        }

        binding.btnGetShared.setOnClickListener {
            getFromShared()
        }

    }

    private fun getFromShared() {
        val name = sharedPref.getString("name", "")

        if(!name.isNullOrEmpty()){
            binding.nameShared.text = "SHAREDPREF: $name"
        }
    }

    private fun setupCart() {
        val adapter = CartDBHelper(this).getCartProducts()
        binding.rviewCart.visibility = View.VISIBLE
        binding.rviewCart.layoutManager = LinearLayoutManager(this)
        binding.rviewCart.adapter = CartAdapter(adapter,  getWindow().getDecorView(), this)
    }

    private fun initSharedPref() {
        sharedPref = this.getSharedPreferences("appsqllite", Context.MODE_PRIVATE)
    }

    private fun saveOnSharedPref(name: String) {
        val prefEditor = sharedPref.edit()
        prefEditor.putString("name", name)
        prefEditor.commit()
    }
}