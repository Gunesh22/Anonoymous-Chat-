package Authentication

import UserInfo.UserInfoDataClass
import UserInfo.UserInfoDatabase
import UserInfo.UserInfoRepository
import UserInfo.UserInfoViewModel
import UserInfo.UserInfoViewModelFactory
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.try2.MainActivity
import com.example.try2.R
import com.example.try2.databinding.FragmentNameNewAccountBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class NameNewAccountFragment : Fragment() {
    private lateinit var binding: FragmentNameNewAccountBinding
    private lateinit var navController: NavController
    private lateinit var viewModel: UserInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNameNewAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()

        // Initialize the database, DAO, repository, and ViewModel outside of the coroutine
        val database = UserInfoDatabase.getDatabase(requireContext())
        val repository = UserInfoRepository(database.userInfoDAO())
        val viewModelFactory = UserInfoViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(UserInfoViewModel::class.java)

        binding.btnNameNewAccount.setOnClickListener {
            val phoneNumber = arguments?.getString("mobile").orEmpty()
            val firstname = binding.firstNameEditTextNewAccount.text.toString().trim().lowercase()
            val lastname = binding.lastNameEditTextNewAccount.text.toString().trim().lowercase()
            val uid = FirebaseAuth.getInstance().currentUser?.uid.toString()

            val userInfo = UserInfoDataClass(uid, firstname, lastname, phoneNumber)

            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.insertUserInfo(userInfo)
                val intent = Intent(requireContext(), MainActivity::class.java)
                intent.putExtra("destinationFragmentId", R.id.profileFragment)
                startActivity(intent)
            }
        }
    }
}
