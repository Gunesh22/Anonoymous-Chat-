package com.example.try2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.try2.databinding.FragmentNameNewAccountBinding
import com.example.try2.databinding.FragmentSplashNewAccountBinding


class NameNewAccountFragment : Fragment() {
    private lateinit var binding: FragmentNameNewAccountBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNameNewAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        binding.btnNameNewAccount.setOnClickListener {
            navController.navigate(R.id.action_nameNewAccountFragment_to_numberNewAccountFragment)
        }
    }

}