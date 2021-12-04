package hu.bme.aut.carlog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import hu.bme.aut.carlog.data.Car
import hu.bme.aut.carlog.data.CarLogDatabase
import hu.bme.aut.carlog.databinding.ActivityDetailsBinding

// TODO: add 2 fragment, implement its intent to the LIST with a parameter of the carID, the two fragments need 2 RV
class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var database: CarLogDatabase
    private var car: Car? = null
    private var carId: Long? = null

    companion object {
        private const val TAG = "DetailsActivity"
        const val EXTRA_CAR_ID = "extra.id"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = CarLogDatabase.getDatabase(applicationContext)
        carId?.let { intent.getLongExtra("CAR_ID", it) }
        getCarFromId(carId)

    }
    private fun getCarFromId(carId: Long?){
        Thread{
            car = database.carDao().getCarById(carId)
            binding.carName.text = car!!.name
        }
    }
}




