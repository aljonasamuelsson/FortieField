package com.example.fortifield.ui
import SoldierOrientationAdapter
import androidx.recyclerview.widget.RecyclerView

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager


import com.example.fortifield.R

import com.example.fortifield.databinding.FragmentSoldierOrientationBinding
import com.example.fortifield.simulation.Orientation
import com.example.fortifield.simulation.OrientationDeterminer

// This fragment is responsible for displaying the soldier's orientation
class SoldierOrientationFragment : Fragment() {
    private var _binding: FragmentSoldierOrientationBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView
    private lateinit var soldierorientationAdapter: SoldierOrientationAdapter
    private lateinit var orientationDeterminer: OrientationDeterminer

    private val orientationsList: List<OrientationDeterminer> = OrientationDeterminer.getMockOrientations()




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("SoldierOrientationFragment", "SoldierOri-view is being created")
        _binding = FragmentSoldierOrientationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("SoldierOrientationFragment", "SoldierOri-view has been created")


        soldierorientationAdapter = SoldierOrientationAdapter(orientationsList)
        binding.soldierOrientationRecyclerView.adapter = soldierorientationAdapter
        binding.soldierOrientationRecyclerView.layoutManager = LinearLayoutManager(context)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d("SoldierOrientationFragment", "SoldierOri-view has been destroyed")
    }
}


