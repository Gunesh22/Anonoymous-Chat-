package Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.try2.AnonymousChatFragment
import com.example.try2.NearbyFragment

class AnonymousVPAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 2 // Number of tabs
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> NearbyFragment() // Assuming you have a fragment called NearbyFragment
            1 -> AnonymousChatFragment() // Assuming you have a fragment called AnonymousChatFragment
            else -> throw IllegalStateException("Unexpected position: $position")
        }
    }
}
