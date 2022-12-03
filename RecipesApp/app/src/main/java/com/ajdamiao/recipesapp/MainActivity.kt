package com.ajdamiao.recipesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.ajdamiao.recipesapp.databinding.ActivityMainBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseApp.initializeApp(this)
        setSupportActionBar(binding.toolbarInclude.toolbar)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainContent) as NavHostFragment
        navController = navHostFragment.navController
        setupNavController()
        setUpNavigation()
        bottomNavigationListener()
        hideNavbar()
        hideToolbar()
        userIsAlreadyLoggedIn()
    }

    private fun userIsAlreadyLoggedIn() {
        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        if(currentUser != null){
            navController.navigate(R.id.homeFragment)
        }
    }

    fun changeToolbarTittle(tittle: String) {
        binding.toolbarInclude.tittleToolbar.text = tittle
    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController, binding.mainActivityMain)
    }

    private fun setupNavController() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.mainContent) as NavHostFragment
        navController = navHostFragment.navController
    }

    private fun setUpNavigation() {
        navController.addOnDestinationChangedListener { _: NavController, nd: NavDestination, _: Bundle? ->
            if (nd.id == R.id.homeFragment || nd.id == R.id.signUpFragment || nd.id == R.id.loginFragment || nd.id == R.id.favoriteRecipesFragment) {
                supportActionBar?.setDisplayHomeAsUpEnabled(false)
            } else {
                supportActionBar?.setDisplayHomeAsUpEnabled(true)
            }
        }
    }

    private fun hideNavbar() {
        navController.addOnDestinationChangedListener { _: NavController, nd: NavDestination, _: Bundle? ->
            if (nd.id == R.id.signUpFragment || nd.id == R.id.loginFragment) {
                binding.bottomNavigation.visibility = View.GONE
            } else {
                binding.bottomNavigation.visibility = View.VISIBLE
            }
        }
    }

    private fun hideToolbar() {
        navController.addOnDestinationChangedListener { _: NavController, nd: NavDestination, _: Bundle? ->
            if (nd.id == R.id.signUpFragment || nd.id == R.id.loginFragment) {
                binding.toolbarInclude.toolbar.visibility = View.GONE
            } else {
                binding.toolbarInclude.toolbar.visibility = View.VISIBLE
            }
        }
    }

    private fun bottomNavigationListener() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menuHome -> { navController.navigate(R.id.homeFragment) }
                R.id.menuFavorites -> { navController.navigate(R.id.favoriteRecipesFragment) }
            }
            true
        }
    }
}