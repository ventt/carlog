package hu.bme.aut.carlog.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.carlog.DetailsActivity
import hu.bme.aut.carlog.adapter.ServiceListAdapter
import hu.bme.aut.carlog.data.CarLogDatabase
import hu.bme.aut.carlog.data.Service
import hu.bme.aut.carlog.databinding.ActivityServiceDetailsFragmentBinding
import hu.bme.aut.carlog.fragments.NewFillUpItemDialogFragment
import kotlin.concurrent.thread

class ServiceDetailsFragment : Fragment(), ServiceListAdapter.OnServiceSelectedListener {
    private lateinit var binding: ActivityServiceDetailsFragmentBinding
    private lateinit var adapter: ServiceListAdapter
    private lateinit var database: CarLogDatabase
    private lateinit var activity: DetailsActivity
    override fun onCreate(savedInstanceState: Bundle?) {
        activity = getActivity() as DetailsActivity
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ActivityServiceDetailsFragmentBinding.inflate(LayoutInflater.from(context))
        database = context?.let { CarLogDatabase.getDatabase(it) }!!
        binding.fabService.setOnClickListener{
            NewFillUpItemDialogFragment().show( //TODO NewServiceItemDialogFragment().show
                activity.supportFragmentManager,
                NewFillUpItemDialogFragment.TAG
            )
        }
        initRecycleView()
        return binding.root
    }
    private fun initRecycleView(){
        adapter = ServiceListAdapter(this)

        binding.rvFueling.layoutManager = LinearLayoutManager(activity)
        binding.rvFueling.adapter = adapter

        loadItemsInBackground()
    }
    private fun loadItemsInBackground() {
        thread {
            val items = activity.carId?.let { database.ServiceDao().getServiceListByCarId(it) }
            activity?.runOnUiThread(){
                if (items != null) {
                    adapter.update(items)
                }
            }
        }
    }
    override fun onServiceSelectedDelete(service: Service) {
        thread {
            service?.let { database.ServiceDao().delete(it) }
            activity.runOnUiThread{
                adapter.removeService(service)
            }
        }
    }
}