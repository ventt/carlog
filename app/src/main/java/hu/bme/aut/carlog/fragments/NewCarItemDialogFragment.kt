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

class NewCarItemDialogFragment : DialogFragment() {
    interface NewCarItemDialogListener{
        fun onCarItemCreated(newCar: Car)
    }

    private lateinit var listener: NewCarItemDialogListener
    private lateinit var binding: FragmentDialogNewCarBinding
    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as? NewCarItemDialogListener ?: throw RuntimeException("Activity must implement the NewCarItemDIalogListener!")
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentDialogNewCarBinding.inflate(LayoutInflater.from(context))
        return AlertDialog.Builder(requireContext())
            .setTitle("New Car")
            .setView(binding.root)
            .setPositiveButton("OK") {
                    _, _ ->
                if(isValid()){
                    listener.onCarItemCreated(getCarItem())
                }
            }
            .setNegativeButton("Cancel",null)
            .create()
    }
    private fun isValid() = binding.etName.text.isNotEmpty()

    private fun getCarItem() = Car(
        name = binding.etName.text.toString(),
        manufacturer = binding.etManuf.text.toString(),
        type = binding.etType.text.toString(),
        produceDate = binding.etType.text.toString().toIntOrNull() ?: 0
    )
    companion object{
        const val TAG = "NewCarItemDialogFragment"
    }
}