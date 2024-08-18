package com.example.try2

import UserInfo.UserInfoViewModel
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.try2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding
    private lateinit var userInfoViewModel: UserInfoViewModel
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Correctly get NavHostFragment
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container) as NavHostFragment
        navController = navHostFragment.navController

        // Set up BottomNavigationView with NavController
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navChatButton -> {
                    navController.navigate(R.id.chatFragment)
                    true
                }

                R.id.navProfileButton -> {
                    navController.navigate(R.id.profileFragment)
                    true
                }
                R.id.navSearchButton -> {
                    navController.navigate(R.id.searchFragment)
                    true
                }

                else -> false
            }
        }

        // Set the default selected item in the BottomNavigationView
        if (savedInstanceState == null) {
            binding.bottomNavigation.selectedItemId = R.id.navChatButton
        }
    }

}




