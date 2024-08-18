package Authentication

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.try2.R
import com.example.try2.databinding.FragmentNumberNewAccountBinding
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class NumberNewAccountFragment : Fragment() {
    private lateinit var binding: FragmentNumberNewAccountBinding
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNumberNewAccountBinding.inflate(inflater, container, false)
        navController = findNavController()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val inputMobile = binding.inputMobile
        val buttonGetOTP = binding.buttonGetOTP
        val progressBar = binding.progressBar
        val countryCodePicker = binding.countryCode

        buttonGetOTP.setOnClickListener {
            val phoneNumber = inputMobile.text.toString()
            countryCodePicker.registerCarrierNumberEditText(inputMobile)

            if (!countryCodePicker.isValidFullNumber) {
                inputMobile.error = "Phone number is not valid"
                return@setOnClickListener
            }

            // Hide button, show progress bar
            buttonGetOTP.visibility = View.INVISIBLE
            progressBar.visibility = View.VISIBLE

            // Delay for 1.5 seconds before starting the OTP process
            Handler(Looper.getMainLooper()).postDelayed({
                val options = PhoneAuthOptions.newBuilder()
                    .setPhoneNumber(countryCodePicker.fullNumberWithPlus)
                    .setTimeout(60L, TimeUnit.SECONDS)
                    .setActivity(requireActivity())
                    .setCallbacks(object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                        override fun onVerificationCompleted(phoneAuthCredential: PhoneAuthCredential) {
                            progressBar.visibility = View.GONE
                            buttonGetOTP.visibility = View.VISIBLE
                        }

                        override fun onVerificationFailed(e: FirebaseException) {
                            progressBar.visibility = View.GONE
                            buttonGetOTP.visibility = View.VISIBLE
                            Toast.makeText(activity, e.message, Toast.LENGTH_SHORT).show()
                        }

                        override fun onCodeSent(verificationId: String, forceResendingToken: PhoneAuthProvider.ForceResendingToken) {
                            progressBar.visibility = View.GONE
                            buttonGetOTP.visibility = View.VISIBLE

                            val bundle = Bundle().apply {
                                putString("mobile", phoneNumber)
                                putString("verificationId", verificationId)
                            }

                            navController.navigate(R.id.action_numberNewAccountFragment_to_otpNewAccountFragment, bundle)
                        }
                    })
                    .build()
                PhoneAuthProvider.verifyPhoneNumber(options)
            }, 1000) // 1 seconds delay
        }
    }
}
