package hu.bme.aut.carlog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.FragmentActivity
import com.google.android.material.tabs.TabLayoutMediator
import hu.bme.aut.carlog.data.Car
import hu.bme.aut.carlog.data.CarLogDatabase
import hu.bme.aut.carlog.data.fillUp
import hu.bme.aut.carlog.databinding.ActivityDetailsBinding
import hu.bme.aut.carlog.fragments.NewFillUpItemDialogFragment
import hu.bme.aut.carlog.fragments.details.DetailsPagerAdapter
import hu.bme.aut.carlog.fragments.details.FuelingDetailsFragment
import hu.bme.aut.carlog.fragments.details.ServiceDetailsFragment
import kotlinx.coroutines.Dispatchers
import kotlin.concurrent.thread

// TODO: add 2 fragment, implement its intent to the LIST with a parameter of the carID, the two fragments need 2 RV
class DetailsActivity : AppCompatActivity(), NewFillUpItemDialogFragment.NewFillUpItemDialogListener {
    private lateinit var binding: ActivityDetailsBinding
    private lateinit var database: CarLogDatabase
    private var car: Car? = null
    var carId: Long? = null

    companion object {
        private const val TAG = "DetailsActivity"
        const val EXTRA_CAR_ID = "extra.id"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database = CarLogDatabase.getDatabase(applicationContext)
        var bundle : Bundle ? = intent.extras
        if(bundle!=null){
            carId = bundle.getLong("extra.id")
        }

        getCarFromId(carId)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    override fun onResume() {
        super.onResume()
        val detailsPagerAdapter = DetailsPagerAdapter(this)
        binding.mainViewPager.adapter = detailsPagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.mainViewPager) { tab, position ->
            tab.text = when(position) {
                0 -> "Fuelling"
                1 -> "Service"
                else -> ""
            }
        }.attach()
    }

    private fun getCarFromId(carId: Long?){
        Thread{
            car = carId?.let { database.carDao().getCarById(it) }
            supportActionBar?.title = car?.name
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onFillUpItemCreated(newFillUp: fillUp) {
        newFillUp.carId = carId
        thread {
            database.fillUpDao().insert(newFillUp)
        }
    }
}




