package com.ajdamiao.recipesapp.view

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.ajdamiao.recipesapp.MainActivity
import com.ajdamiao.recipesapp.R
import com.ajdamiao.recipesapp.databinding.FragmentLoginBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment(R.layout.fragment_login) {
   private lateinit var binding: FragmentLoginBinding
   private lateinit var auth: FirebaseAuth

   override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
      super.onViewCreated(view, savedInstanceState)

      binding = FragmentLoginBinding.bind(view)
      auth = FirebaseAuth.getInstance()

      binding.btnSignInApp.setOnClickListener {
         Navigation.findNavController(requireView()).navigate(R.id.signUpFragment)
      }

      binding.btnSignInApp.setOnClickListener {
         signInEmailAndPassword(binding.txtEmail.text.toString(), binding.txtPass.text.toString())
      }
   }

   private fun signInEmailAndPassword(email: String, password: String) {
      auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { response ->
         if(response.isSuccessful) {
            val uid = response.result.user?.uid

            if(uid != null) {
               Navigation.findNavController(requireView()).navigate(R.id.homeFragment)
            }
         }
         else {
            Toast.makeText(requireContext(),"Erro ao fazer login", Toast.LENGTH_SHORT).show()
            println(response.getResult())
         }
      }
   }
}