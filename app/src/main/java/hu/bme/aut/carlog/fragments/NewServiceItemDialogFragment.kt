package hu.bme.aut.carlog.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import hu.bme.aut.carlog.data.Service
import hu.bme.aut.carlog.data.fillUp
import hu.bme.aut.carlog.databinding.FragmentNewFillUpItemDialogBinding
import hu.bme.aut.carlog.databinding.FragmentNewServiceItemDialogBinding
import java.lang.RuntimeException
import java.util.*

class NewServiceItemDialogFragment : DialogFragment() {
    interface NewServiceItemDialogListener{
        fun onServiceItemCreated(service: Service)
    }
    private fun getDateFrom(picker: DatePicker): String {
        return String.format(
            Locale.getDefault(), "%04d.%02d.%02d.",
            picker.year, picker.month + 1, picker.dayOfMonth
        )
    }

    private lateinit var listener: NewServiceItemDialogListener
    private lateinit var binding: FragmentNewServiceItemDialogBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? NewServiceItemDialogListener ?: throw RuntimeException("Activity must implement the NewServiceItemDialogFragment!")

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentNewServiceItemDialogBinding.inflate(LayoutInflater.from(context))

        return AlertDialog.Builder(requireContext())
            .setTitle("New Service")
            .setView(binding.root)
            .setPositiveButton("OK") {
                    _, _ ->
                if(isValid()){
                    getService().let { listener.onServiceItemCreated(it) }
                }
            }
            .setNegativeButton("Cancel",null)
            .create()
    }
    private fun isValid() = binding.etServiceType.text != null && binding.etServiceOdometer.text != null

    private fun getService() = getDateFrom(binding.dpServiceDate).let {
        Service(
            service_type = binding.etServiceType.text.toString(),
            date = it,
            mileage = Integer.parseInt(binding.etServiceOdometer.text.toString()),
            oil_change = binding.cbFullFueling.isChecked,
            description = binding.etServiceDesc.text.toString()
        )
    }
    companion object{
        const val TAG = "NewServiceItemDialogFragment"
    }
}