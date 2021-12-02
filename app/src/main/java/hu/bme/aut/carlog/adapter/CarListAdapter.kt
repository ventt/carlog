package hu.bme.aut.carlog.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.carlog.data.Car
import hu.bme.aut.carlog.databinding.CarListBinding
import java.util.*

//Start screen car RV adapter implementaion
class CarListAdapter(private val listener: CarListClickListener): RecyclerView.Adapter<CarListAdapter.CarListViewHolder>() {

    private val cars = mutableListOf<Car>() //val for the cars list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CarListViewHolder(
        CarListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    ) //attach the layout

    //fill the layout with car datas
    override fun onBindViewHolder(holder: CarListViewHolder, position: Int) {
        val carItem = cars[position]

        holder.binding.carName.text = carItem.name
        holder.binding.carManufac.text = carItem.manufacturer
        holder.binding.carType.text = carItem.type
        holder.binding.produceDate.text = carItem.produceDate.toString()

    }
    fun addItem(item: Car){
        cars.add(item)
        notifyItemInserted(cars.size - 1)
    }

    fun update(carList: List<Car>){
        cars.clear()
        cars.addAll(carList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = cars.size

    interface CarListClickListener{
        fun onItemChanged(car: Car)
    }
    inner class CarListViewHolder(val binding: CarListBinding) : RecyclerView.ViewHolder(binding.root)


}