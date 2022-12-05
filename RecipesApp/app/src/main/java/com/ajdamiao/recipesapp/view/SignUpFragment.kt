package com.ajdamiao.recipesapp.view

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.ajdamiao.recipesapp.R
import com.ajdamiao.recipesapp.databinding.FragmentSignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpFragment : Fragment(R.layout.fragment_sign_up) {
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSignUpBinding.bind(view)
        auth = FirebaseAuth.getInstance()

        binding.btnSignUpApp.setOnClickListener {
            createNewUser(binding.txtEmail.text.toString(), binding.txtPass.text.toString())
        }
    }

    private fun createNewUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener { response ->

            if(response.isSuccessful) {
                Navigation.findNavController(requireView()).navigate(R.id.homeFragment)
            }
            else {
                Toast.makeText(requireContext(), "Error creating user", Toast.LENGTH_SHORT).show()
            }
        }
    }
}