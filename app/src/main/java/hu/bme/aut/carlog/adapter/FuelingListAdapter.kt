package hu.bme.aut.carlog.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.carlog.R
import hu.bme.aut.carlog.data.FuelConsumptionCalc
import hu.bme.aut.carlog.data.fillUp
import hu.bme.aut.carlog.databinding.FuelingListItemBinding

class FuelingListAdapter(private val listener: OnFillUpSelectedListener) : RecyclerView.Adapter<FuelingListAdapter.FillUpViewHolder>() {
    private val listOfFuelings = mutableListOf<fillUp>()

    interface OnFillUpSelectedListener {
        fun onFillUpSelectedDelete(fillUp: fillUp?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FillUpViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fueling_list_item, parent, false)
        return FillUpViewHolder(view)
    }

    override fun onBindViewHolder(holder: FillUpViewHolder, position: Int) {
        val item = listOfFuelings[position]
        holder.bind(item)
        holder.binding.ibDelete.setOnClickListener { listener.onFillUpSelectedDelete(item) }
        if(position >= 1 && listOfFuelings[position-1] != null && listOfFuelings[position-1].full_fueling){
            holder.binding.fuelingConsumption.text = FuelConsumptionCalc.getConsumption(listOfFuelings[position-1].odometer,item.odometer, item.quantity).toString()
        }else{
            holder.binding.fuelingConsumption.text = "Can't compute!"
            holder.binding.literPerHundred.text = "Make a full tank"
        }
        holder.binding.fuelingDate.text = item.date
        holder.binding.fuelingOdometer.text = item.odometer.toString()
        holder.binding.fuelingQunatity.text = item.quantity.toString() + " L"

        if(position >= 1 && listOfFuelings[position-1] != null){
            holder.binding.tripOdometer.text = FuelConsumptionCalc.getTrip(listOfFuelings[position-1].odometer, item.odometer).toString() + " km"
        }else{
            holder.binding.tripOdometer.text = "0 km"
        }
    }

    override fun getItemCount(): Int = listOfFuelings.size

    fun addFueling(newFueling: fillUp) {
        listOfFuelings.add(newFueling)
        notifyItemInserted(listOfFuelings.size - 1)
    }
    fun update(fillUp: List<fillUp>){
        listOfFuelings.clear()
        listOfFuelings.addAll(fillUp)
        notifyDataSetChanged()
    }
    fun removeFueling(fillUp: fillUp?) {
        listOfFuelings.remove(fillUp)
        notifyItemRemoved(listOfFuelings.indexOf(fillUp))
        if (listOfFuelings.indexOf(fillUp) < listOfFuelings.size) {
            notifyItemRangeChanged(listOfFuelings.indexOf(fillUp), listOfFuelings.size - listOfFuelings.indexOf(fillUp))
        }
    }

    inner class FillUpViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = FuelingListItemBinding.bind(itemView)
        var item: fillUp? = null

        /*init {
            binding.root.setOnClickListener { listener.onFillUpSelected(item) }
        }*/

        fun bind(newFueling: fillUp) {
            item = newFueling

        }
    }
}