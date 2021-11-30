package hu.bme.aut.carlog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import hu.bme.aut.carlog.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var helloWorldText = "HelloWorld"
        binding.helloWorldText.text = helloWorldText;
    }

}