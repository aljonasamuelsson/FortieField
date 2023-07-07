package com.example.fortifield.ui

import android.util.Log

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import com.example.fortifield.R
import com.example.fortifield.databinding.FragmentWeaponSystemControlBinding
import com.example.fortifield.simulation.Orientation
import com.example.fortifield.simulation.OrientationDeterminer
import com.example.fortifield.simulation.Position
import com.example.fortifield.simulation.Soldier
import com.example.fortifield.simulation.WeaponSystem

class WeaponSystemControlFragment : Fragment() {
    private var _binding: FragmentWeaponSystemControlBinding? = null
    private val binding get() = _binding!!

    // Created a mock OrientationDeterminer
    private val mockOrientation =
        OrientationDeterminer(Position(2f,0f),Orientation(System.currentTimeMillis(),0.0), "FORWARD", "UP")

    private val weaponSystem = WeaponSystem(mockOrientation)



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWeaponSystemControlBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.fireButton.setOnClickListener {
            weaponSystem.fire()
            updateWeaponStatus()
            //handle weapon firing
        }

        binding.aimControl.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                //update the aim label with the current aim value
                binding.aimLabel.text = "Aim: $progress "
                // Handle weapon aiming
                // The `progress` parameter represents the current position of the slider.
                // You can use this value to adjust the aim of the weapon.
                weaponSystem.orientationDeterminer.weaponPosition = if (progress > 100) "UP" else "DOWN"
                updateWeaponStatus()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }


    // Call this method whenever the weapon status changes
    fun updateWeaponStatus() {
        val status = if(weaponSystem.isRaised()) "Raised" else "Lowered"
        binding.weaponStatus.text = "Weapon Status: $status"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
