package hu.bme.aut.carlog

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.carlog.adapter.CarListAdapter
import hu.bme.aut.carlog.data.Car
import hu.bme.aut.carlog.data.CarLogDatabase
import hu.bme.aut.carlog.databinding.ActivityMainBinding
import hu.bme.aut.carlog.fragments.NewCarItemDialogFragment
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity(), CarListAdapter.CarListClickListener, NewCarItemDialogFragment.NewCarItemDialogListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: CarLogDatabase
    private lateinit var adapter: CarListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        database = CarLogDatabase.getDatabase(applicationContext)
        binding.fab.setOnClickListener{
            NewCarItemDialogFragment().show(
                supportFragmentManager,
                NewCarItemDialogFragment.TAG
            )
        }

        initRecycleView()
    }
    private fun initRecycleView(){
        adapter = CarListAdapter(this)
        binding.rvMain.layoutManager = LinearLayoutManager(this)
        binding.rvMain.adapter = adapter

        loadItemsInBackground()
    }

    override fun onCarSelected(car: Car?) {
        val detailsActivityIntent = Intent()
        detailsActivityIntent.setClass(this@MainActivity, DetailsActivity::class.java)
        detailsActivityIntent.putExtra("CAR_ID", car?.id)
        startActivity(detailsActivityIntent)
    }

    private fun loadItemsInBackground() {
        thread {
            val items = database.carDao().getAll()
            runOnUiThread{
                adapter.update(items)
            }
        }
    }

    override fun onItemChanged(car: Car) {
        thread {
            database.carDao().update(car)
            Log.d("MainActivity", "CarList update was successful")
        }
    }

    override fun onCarItemCreated(newCar: Car) {
        thread {
            val insertId = database.carDao().insert(newCar)
            newCar.id = insertId
            runOnUiThread{
                adapter.addItem(newCar)
            }

        }
    }

}


