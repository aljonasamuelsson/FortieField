package com.example.fortifield.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fortifield.R
import com.example.fortifield.databinding.FragmentCombatSimulationBinding
import com.example.fortifield.databinding.FragmentSensorDataBinding
import com.example.fortifield.rendering.EnvironmentRenderer
import com.example.fortifield.sensors.SensorData

class BackgroundFragment : Fragment() {

    //private var _binding: FragmentCombatSimulationBinding? = null
    //private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_background, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Perform any additional setup or view manipulation here
        // This method is called after the fragment's view has been created
    }
}