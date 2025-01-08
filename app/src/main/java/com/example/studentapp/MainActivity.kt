package com.example.studentapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.example.studentapp.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding
    var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Inflate the binding for 'main_activity.xml'
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Apply window insets to the root view (binding.main).
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Set up the Toolbar as our ActionBar
        setSupportActionBar(binding.baseToolbar)

        // Find the NavHostFragment and set up the NavController
        val navHostController =
            supportFragmentManager.findFragmentById(binding.mainNavHost.id) as? NavHostFragment
        navController = navHostController?.navController

        // If navController is not null, set up the action bar for navigation
        navController?.let {
            NavigationUI.setupActionBarWithNavController(this, it)
        }
    }

}
