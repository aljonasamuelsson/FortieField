package com.example.fortifield.ui

import SoldierOrientationAdapter
import android.content.Context
import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fortifield.databinding.FragmentCombatSimulationBinding
import com.example.fortifield.devices.AndroidDeviceHandler
import com.example.fortifield.rendering.EnvironmentRenderer
import com.example.fortifield.simulation.Orientation
import com.example.fortifield.simulation.OrientationDeterminer
import com.example.fortifield.simulation.Position
import com.example.fortifield.simulation.Soldier
import com.example.fortifield.simulation.WeaponSystem
import kotlin.math.sin

// This fragment is responsible for displaying the combat simulation
class CombatSimulationFragment : Fragment() {
    private lateinit var orientationRecyclerView: RecyclerView
    private lateinit var orientationAdapter: SoldierOrientationAdapter


    // This is the binding object that will be used to access views in the layout
    private var _binding: FragmentCombatSimulationBinding? = null
    private val binding get() = _binding!!

    private val mockOrientation = OrientationDeterminer(Position(3f, 0f), Orientation(System.currentTimeMillis(), 0.0), "FORWARD", "UP")
    private val soldier = Soldier(mockOrientation )

    // Create an EnvironmentRenderer using the WeaponSystem
    private lateinit var environmentRenderer: EnvironmentRenderer
    private lateinit var combatView: CombatView

    private inner class CombatView(context: Context) : View(context) {
        override fun onDraw(canvas: Canvas?) {
            super.onDraw(canvas)
            if (canvas != null) {
                environmentRenderer.drawEnvironment(canvas)
            }
        }

        override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
            val width = View.MeasureSpec.getSize(widthMeasureSpec)
            val height = View.MeasureSpec.getSize(heightMeasureSpec)
            setMeasuredDimension(width, height)
        }
    }

    // This method is called when the view for this fragment is created
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCombatSimulationBinding.inflate(inflater, container, false)

        environmentRenderer = EnvironmentRenderer(soldier)
        combatView = CombatView(requireContext())

        binding.combatSimulationLayout.addView(combatView)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        combatView.invalidate()

    }

    // This method is called when the view for this fragment is destroyed
    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}
