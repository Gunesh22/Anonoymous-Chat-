package com.example.try2

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.try2.databinding.ActivityNewAccountBinding

class NewAccount : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var binding: ActivityNewAccountBinding
        super.onCreate(savedInstanceState)
        binding = ActivityNewAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}