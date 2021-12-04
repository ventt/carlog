package hu.bme.aut.carlog.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.DialogFragment
import hu.bme.aut.carlog.data.Car
import hu.bme.aut.carlog.databinding.FragmentDialogNewCarBinding
import java.lang.RuntimeException
import android.R
import android.view.View

import android.widget.TimePicker

import android.widget.DatePicker
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*


class NewCarItemDialogFragment : DialogFragment() {
    interface NewCarItemDialogListener{
        fun onCarItemCreated(newCar: Car)
    }
    private fun getDateFrom(picker: DatePicker): String? {
        return String.format(
            Locale.getDefault(), "%04d.%02d.%02d.",
            picker.year, picker.month + 1, picker.dayOfMonth
        )
    }

    private lateinit var listener: NewCarItemDialogListener
    private lateinit var binding: FragmentDialogNewCarBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? NewCarItemDialogListener ?: throw RuntimeException("Activity must implement the NewCarItemDialogListener!")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentDialogNewCarBinding.inflate(LayoutInflater.from(context))

        return AlertDialog.Builder(requireContext())
            .setTitle("New Car")
            .setView(binding.root)
            .setPositiveButton("OK") {
                    _, _ ->
                if(isValid()){
                    getCarItem()?.let { listener.onCarItemCreated(it) }
                }
            }
            .setNegativeButton("Cancel",null)
            .create()
    }
    private fun isValid() = binding.etName.text.isNotEmpty()

    private fun getCarItem() = getDateFrom(binding.dpStartDate)?.let {
        Car(
        name = binding.etName.text.toString(),
        manufacturer = binding.etManuf.text.toString(),
        type = binding.etType.text.toString(),
        produceDate = it
    )
    }
    companion object{
        const val TAG = "NewCarItemDialogFragment"
    }
}