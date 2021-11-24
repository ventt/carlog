package hu.bme.aut.carlog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import hu.bme.aut.carlog.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var state = 0
    private val duration = Toast.LENGTH_SHORT

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        val toast = Toast.makeText(applicationContext, "Am i right?", duration)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        var helloWorldText = "HelloWorld"
        binding.helloWorldText.text = helloWorldText;
        binding.pushBitch.setOnClickListener {
            if(state == 0){
                binding.helloWorldText.text = "You pushed me!"
                binding.pushBitch.text = "Come on push me again!"
                state = 1
            }else{
                binding.helloWorldText.text = "Momma thicc"
                toast.show()
                state = 0
            }
        }
    }

}