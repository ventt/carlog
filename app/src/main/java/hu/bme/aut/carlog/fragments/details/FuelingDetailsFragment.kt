package hu.bme.aut.carlog.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.carlog.DetailsActivity
import hu.bme.aut.carlog.adapter.FuelingListAdapter
import hu.bme.aut.carlog.data.CarLogDatabase
import hu.bme.aut.carlog.data.fillUp
import hu.bme.aut.carlog.data.fillUpDao
import hu.bme.aut.carlog.databinding.ActivityFuelingDetailsFragmentBinding
import hu.bme.aut.carlog.fragments.NewCarItemDialogFragment
import hu.bme.aut.carlog.fragments.NewFillUpItemDialogFragment
import kotlin.concurrent.thread

class FuelingDetailsFragment :  Fragment(), FuelingListAdapter.OnFillUpSelectedListener {
    private lateinit var binding: ActivityFuelingDetailsFragmentBinding
    private lateinit var adapter: FuelingListAdapter
    private lateinit var database: CarLogDatabase
    private lateinit var activity: DetailsActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        activity = getActivity() as DetailsActivity
        super.onCreate(savedInstanceState)


    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = ActivityFuelingDetailsFragmentBinding.inflate(LayoutInflater.from(context))
        database = context?.let { CarLogDatabase.getDatabase(it) }!!
        binding.fabFueling.setOnClickListener{
            NewFillUpItemDialogFragment().show(
                activity.supportFragmentManager,
                NewFillUpItemDialogFragment.TAG
            )
        }
        initRecycleView()
        return binding.root
    }

    private fun initRecycleView(){
        adapter = FuelingListAdapter(this)

        binding.rvFueling.layoutManager = LinearLayoutManager(activity)
        binding.rvFueling.adapter = adapter

        loadItemsInBackground()
    }

    private fun loadItemsInBackground() {
        thread {
           val items = activity.carId?.let { database.fillUpDao().getFuellingFromCarId(it) }
            activity?.runOnUiThread(){
                if (items != null) {
                    adapter.update(items)
                }
            }
        }
    }

    override fun onFillUpSelected(fillUp: fillUp?) {
        Toast.makeText(context, fillUp?.odometer.toString(),Toast.LENGTH_SHORT)
    }
}