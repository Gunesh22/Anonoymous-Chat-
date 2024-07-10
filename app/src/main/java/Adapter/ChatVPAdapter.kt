package Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.try2.NormalChatFragment
import com.example.try2.RecieveAnonymousChatFragment

class ChatVPAdapter(FragmentActivity: FragmentActivity): FragmentStateAdapter(FragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> NormalChatFragment()
            1 -> RecieveAnonymousChatFragment()
            else -> throw IllegalStateException("Unexpected position: $position")
        }
    }

}