package com.example.try2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.try2.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        val useruid = arguments?.getString("uid")
        loadProfile(useruid)


    }

    private fun loadProfile(useruid: String?) {
        if (useruid != null) {
            // Load profile based on provided UID
            val db = FirebaseFirestore.getInstance()
            val userinfoCollection = db.collection("user_info")

            val uidSearch  = userinfoCollection
                .whereEqualTo("uid", useruid)

            uidSearch.get().addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    val document = querySnapshot.documents[0] // Get the first document
                    val firstName = document.getString("firstName")
                    val lastName = document.getString("lastname")
                    val name = "$firstName $lastName"
                    binding.userName.text = name
                } else {
                    binding.userName.text = "User not found"
                }

            }
                .addOnFailureListener { exception ->
                    // Handle errors
                }
        } else {
            // Load current user's profile
            val currentUser = FirebaseAuth.getInstance().currentUser
            val uid = currentUser?.uid
            if (uid != null) {
                val userCollection = FirebaseFirestore.getInstance().collection("user_info")
                val currentUserCollection = userCollection.whereEqualTo("uid", uid)
                currentUserCollection.get().addOnSuccessListener { querySnapshot ->
                    if (!querySnapshot.isEmpty) {
                        val document = querySnapshot.documents[0] // Get the first document
                        val firstName = document.getString("firstName")
                        val lastName = document.getString("lastname")
                        val name = "$firstName $lastName"
                        binding.userName.text = name
                    } else {
                        binding.userName.text = "Current user not found"
                    }

                }
                    .addOnFailureListener { exception ->
                        // Handle errors
                    }
            }
        }
    }
}