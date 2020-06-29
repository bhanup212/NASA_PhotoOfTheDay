package com.bhanu.nasaphotooftheday.ui

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import coil.api.load
import com.bhanu.nasaphotooftheday.R
import com.bhanu.nasaphotooftheday.databinding.FragmentDashboardBinding
import com.bhanu.nasaphotooftheday.model.Apod
import com.bhanu.nasaphotooftheday.ui.viewmodel.NasaViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.text.SimpleDateFormat
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [DashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DashboardFragment : Fragment() {

    companion object{
        private const val TAG = "DashboardFragment"
    }

    private val viewModel: NasaViewModel by sharedViewModel()

    private lateinit var binding:FragmentDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this, Callback())
    }
    inner class Callback : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            activity?.finish()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDashboardBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModels()
        clickListeners()
        viewModel.getApod(getDate())
    }

    private fun observeViewModels(){
        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            binding.progressbar.isVisible = it
        })
        viewModel.apod.observe(viewLifecycleOwner, Observer {
            updateUi(it)
        })
        viewModel.errorMsg.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(),it, Toast.LENGTH_SHORT).show()
        })
    }
    private fun clickListeners(){
        binding.calender.setOnClickListener {
            showDatePicker()
        }
        binding.zoomIn.setOnClickListener {
            if (binding.imageView.scaleX < 2.9f) {
                binding.imageView.scaleX = binding.imageView.scaleX + 0.1f
                binding.imageView.scaleY = binding.imageView.scaleY + 0.1f
            }
        }
    }
    private fun updateUi(apod: Apod){
        binding.apply {
            imageView.invalidate()
            imageView.load(apod.hdurl)
            title.text = apod.title
            description.text = apod.explanation
        }
    }
    private fun showOrHideUI(isLoading: Boolean){
        binding.description.isVisible = isLoading
        binding.imageView.isVisible = isLoading
    }
    private fun showDatePicker(){
        val currentDate = Calendar.getInstance()
        val year = currentDate.get(Calendar.YEAR)
        val month = currentDate.get(Calendar.MONTH)
        val day = currentDate.get(Calendar.DAY_OF_MONTH)

        val datePicker = DatePickerDialog(requireContext(),
            DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val mnth = if (month <9) "0${month+1}" else "${month+1}"
                val day = if (dayOfMonth <9) "0${dayOfMonth}" else "$dayOfMonth"
                val date = "$year-$mnth-$day"

                viewModel.getApod(date)
            },year,month,day)
        datePicker.datePicker.maxDate = Calendar.getInstance().timeInMillis
        datePicker.show()
    }

    private fun getDate():String{
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val calender = Calendar.getInstance()
        return sdf.format(Date())
    }
}