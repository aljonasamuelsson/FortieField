package com.example.fortifield.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fortifield.R
import com.example.fortifield.databinding.FragmentSensorDataBinding
import com.example.fortifield.sensors.SensorData

//This fragment is responsible for collecting and displaying sensor data
class SensorDataFragment : Fragment() {

    //This is the binding object that will be used to access views in the layout
    private var _binding: FragmentSensorDataBinding? = null
    private val binding get() = _binding!!

    // Declare sensorDataAdapter
    private lateinit var sensorDataAdapter: SensorDataAdapter
    private val sensorDataList: List<SensorData> = SensorData.createMockData()


    //This method is called when the view for this fragment is created
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        Log.d("SensorDataFragment", "SensorData-view is being created")
        //Inflate the layout for this fragment using the binding object
        _binding = FragmentSensorDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    //This method is called after the view for this fragment has been created
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("SensorDataFragment", "SensorData-view has been created")

        sensorDataAdapter =
            SensorDataAdapter(sensorDataList) // TODO: Replace with my actual sensor data
        binding.sensorDataRecyclerView.adapter = sensorDataAdapter
        binding.sensorDataRecyclerView.layoutManager = LinearLayoutManager(context)

    }

    //This method is called when the view for this fragment is destroyed
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d("SensorDataFragment", "SensorData-view has been destroyed")
    }
}

