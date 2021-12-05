package hu.bme.aut.carlog.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import hu.bme.aut.carlog.DetailsActivity
import hu.bme.aut.carlog.data.Car
import hu.bme.aut.carlog.data.fillUp
import hu.bme.aut.carlog.databinding.FragmentDialogNewCarBinding
import hu.bme.aut.carlog.databinding.FragmentNewFillUpItemDialogBinding
import java.lang.RuntimeException
import java.util.*

class NewFillUpItemDialogFragment : DialogFragment() {
    interface NewFillUpItemDialogListener{
        fun onFillUpItemCreated(newFillUp: fillUp)
    }
    private fun getDateFrom(picker: DatePicker): String {
        return String.format(
            Locale.getDefault(), "%04d.%02d.%02d.",
            picker.year, picker.month + 1, picker.dayOfMonth
        )
    }

    private lateinit var listener: NewFillUpItemDialogListener
    private lateinit var binding: FragmentNewFillUpItemDialogBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? NewFillUpItemDialogListener ?: throw RuntimeException("Activity must implement the NewFillUpDialogFragment!")

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentNewFillUpItemDialogBinding.inflate(LayoutInflater.from(context))

        return AlertDialog.Builder(requireContext())
            .setTitle("New Fill Up")
            .setView(binding.root)
            .setPositiveButton("OK") {
                    _, _ ->
                if(isValid()){
                    getFillUp().let { listener.onFillUpItemCreated(it) }
                }
            }
            .setNegativeButton("Cancel",null)
            .create()
    }
    private fun isValid() = binding.etOdometer.text != null && binding.etQuantity.text != null

    private fun getFillUp() = getDateFrom(binding.dpFuelingDate).let {
        fillUp(
            odometer = Integer.parseInt(binding.etOdometer.text.toString()),
            quantity =  binding.etQuantity.text.toString().toFloat(),
            date = it,
            price = binding.etPrice.text.toString().toFloat(),
            full_fueling = binding.cbFullFueling.isChecked
            )
    }
    companion object{
        const val TAG = "NewFillUpItemDialogFragment"
    }
}