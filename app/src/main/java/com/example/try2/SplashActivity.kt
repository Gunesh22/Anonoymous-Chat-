package com.example.try2

import Authentication.NewAccount
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.try2.databinding.ActivitySplashBinding


class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.splashBtnCreateNewAccount.setOnClickListener {
            val intent = Intent(this, NewAccount::class.java)
            startActivity(intent)
        }
    }
}