package Authentication

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.try2.MainActivity
import com.example.try2.R
import com.example.try2.databinding.FragmentOtpNewAccountBinding
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.auth.FirebaseAuth

class OtpNewAccountFragment : Fragment() {

    private var _binding: FragmentOtpNewAccountBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController
    private lateinit var mobile: String
    private lateinit var verificationId: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOtpNewAccountBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        binding.changePhoneNumber.setOnClickListener {
            navController.navigate(R.id.action_otpNewAccountFragment_to_numberNewAccountFragment)
        }

        mobile = arguments?.getString("mobile").orEmpty()
        verificationId = arguments?.getString("verificationId").orEmpty()

        setupOtpInputs()

        binding.buttonVerify.setOnClickListener {
            val code = binding.inputCode1.text.toString() +
                    binding.inputCode2.text.toString() +
                    binding.inputCode3.text.toString() +
                    binding.inputCode4.text.toString() +
                    binding.inputCode5.text.toString() +
                    binding.inputCode6.text.toString()

            if (code.isEmpty()) {
                Toast.makeText(activity, "Enter valid code", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            binding.progressBar.visibility = View.VISIBLE
            binding.buttonVerify.visibility = View.INVISIBLE

            if (verificationId.isNotEmpty()) {
                val credential = PhoneAuthProvider.getCredential(verificationId, code)
                signInWithPhoneAuthCredential(credential)
            } else {
                binding.progressBar.visibility = View.GONE
                binding.buttonVerify.visibility = View.VISIBLE
                Toast.makeText(activity, "Verification ID not found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, navigate to UsernameFragment
                    binding.progressBar.visibility = View.GONE
                    binding.buttonVerify.visibility = View.VISIBLE
                    val bundle = Bundle().apply {
                        putString("mobile" , mobile) }
                    navController.navigate(R.id.action_otpNewAccountFragment_to_nameNewAccountFragment,bundle)
                } else {
                    // Sign in failed, display a message
                    binding.progressBar.visibility = View.GONE
                    binding.buttonVerify.visibility = View.VISIBLE
                    Toast.makeText(activity, "Verification failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun setupOtpInputs() {
        binding.apply {
            inputCode1.addTextChangedListener(GenericTextWatcher(inputCode2))
            inputCode2.addTextChangedListener(GenericTextWatcher(inputCode3))
            inputCode3.addTextChangedListener(GenericTextWatcher(inputCode4))
            inputCode4.addTextChangedListener(GenericTextWatcher(inputCode5))
            inputCode5.addTextChangedListener(GenericTextWatcher(inputCode6))
        }
    }

    private inner class GenericTextWatcher(private val nextView: View?) : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        override fun afterTextChanged(s: Editable?) {
            if (!s.isNullOrEmpty()) {
                nextView?.requestFocus()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
