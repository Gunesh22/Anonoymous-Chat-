package com.example.try2

import Adapter.AnonymousVPAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.try2.databinding.FragmentAnonymousBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class AnonymousFragment : Fragment() {

    private var _binding: FragmentAnonymousBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment using View Binding
        _binding = FragmentAnonymousBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize TabLayout and ViewPager2
        val tabLayout = binding.anonymousTabLayout
        val viewPager2 = binding.anonymousViewpager

        // Set up the adapter
        val adapter = AnonymousVPAdapter(requireActivity())
        viewPager2.adapter = adapter

        // Link TabLayout and ViewPager2
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            tab.text = when (position) {
                0 -> "Nearby"
                1 -> "Anonymous Chat"
                else -> throw IllegalStateException("Unexpected position: $position")
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
