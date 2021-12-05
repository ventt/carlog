package hu.bme.aut.carlog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.carlog.R
import hu.bme.aut.carlog.data.FuelConsumptionCalc
import hu.bme.aut.carlog.data.Service
import hu.bme.aut.carlog.data.fillUp
import hu.bme.aut.carlog.databinding.FuelingListItemBinding
import hu.bme.aut.carlog.databinding.ServiceListItemBinding

class ServiceListAdapter(private val listener: OnServiceSelectedListener) : RecyclerView.Adapter<ServiceListAdapter.FillUpViewHolder>() {
    private val listOfService = mutableListOf<Service>()

    interface OnServiceSelectedListener {
        fun onServiceSelectedDelete(service: Service)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FillUpViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.service_list_item, parent, false)
        return FillUpViewHolder(view)
    }

    override fun onBindViewHolder(holder: FillUpViewHolder, position: Int) {
        val item = listOfService[position]
        holder.bind(item)
        holder.binding.ibServiceDelete.setOnClickListener { listener.onServiceSelectedDelete(item) }
        holder.binding.tvServiceType.text = item.service_type
        holder.binding.tvServiceDate.text = item.date
        holder.binding.tvServiceOdometer.text = item.mileage.toString()
        if(item.oil_change){
            holder.binding.tvServiceOilChange.text = "Had oil change."
        }else{
            holder.binding.tvServiceOilChange.text = "No oil change."
        }
        holder.binding.tvServiceDesc.text = item.description

    }

    override fun getItemCount(): Int = listOfService.size

    fun addService(newService: Service) {
        listOfService.add(newService)
        notifyItemInserted(listOfService.size - 1)
    }
    fun update(service: List<Service>){
        listOfService.clear()
        listOfService.addAll(service)
        notifyDataSetChanged()
    }
    fun removeService(service: Service) {
        listOfService.remove(service)
        notifyItemRemoved(listOfService.indexOf(service))
        if (listOfService.indexOf(service) < listOfService.size) {
            notifyItemRangeChanged(listOfService.indexOf(service), listOfService.size - listOfService.indexOf(service))
        }
    }

    inner class FillUpViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = ServiceListItemBinding.bind(itemView)
        var item: Service? = null

        /*init {
            binding.root.setOnClickListener { listener.onFillUpSelected(item) }
        }*/

        fun bind(service: Service) {
            item = service

        }
    }
}