package com.example.try2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.try2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navChatButton -> {
                    replaceFragment(ChatFragment())
                    true
                }
                R.id.navAnonymousButton -> {
                    replaceFragment(AnonymousFragment())
                    true
                }
                R.id.navProfileButton -> {
                    replaceFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }

        // Set default fragment
        if (savedInstanceState == null) {
            binding.bottomNavigation.selectedItemId = R.id.navChatButton
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }

}
