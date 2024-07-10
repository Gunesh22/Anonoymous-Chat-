package com.example.try2

import Adapter.ChatVPAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.try2.databinding.FragmentChatBinding
import com.google.android.material.tabs.TabLayoutMediator


class ChatFragment : Fragment() {

    private var _binding : FragmentChatBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentChatBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tabLayout = binding.chatTabLayout
        val viewPager2 = binding.chatViewpager

        val adapter = ChatVPAdapter(requireActivity())
        viewPager2.adapter = adapter

        TabLayoutMediator(tabLayout,viewPager2) {
            tab,position -> tab.text = when(position){
                0 -> "Normal Chat"
            1 -> "Recieved Anonymous Chat"
            else -> throw IllegalStateException("Unexpected position: $position")
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}