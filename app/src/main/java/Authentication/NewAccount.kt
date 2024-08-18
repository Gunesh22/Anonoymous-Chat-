package Authentication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.try2.databinding.ActivityNewAccountBinding

class NewAccount : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        lateinit var binding: ActivityNewAccountBinding
        super.onCreate(savedInstanceState)
        binding = ActivityNewAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}