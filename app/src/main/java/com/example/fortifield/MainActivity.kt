package com.example.fortifield

import android.Manifest

import SoldierOrientationFragment
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.fortifield.databinding.ActivityMainBinding
import com.example.fortifield.ui.CombatSimulationFragment
import com.example.fortifield.ui.SensorDataFragment
import com.example.fortifield.ui.BackgroundFragment
import com.example.fortifield.ui.WeaponSystemControlFragment
import com.google.android.material.tabs.TabLayoutMediator
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.example.fortifield.devices.AndroidDeviceHandler
import com.google.android.material.tabs.TabLayout


class MainActivity : AppCompatActivity() {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    private lateinit var userInterface: BackgroundFragment
    lateinit var deviceHandler: AndroidDeviceHandler


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("startar appen", "onCreateMAinActivity")
        WindowCompat.setDecorFitsSystemWindows(window, false)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        deviceHandler = AndroidDeviceHandler(this)
        deviceHandler.handleDevice()

        deviceHandler.sensorData.observe(this, Observer { data ->

        })

        // Create an adapter that knows which fragment should be shown on each page
        val adapter = MyPagerAdapter(this)

        // Set the adapter onto the view pager
        binding.viewPager.adapter = adapter

        binding.tabs.tabMode = TabLayout.MODE_SCROLLABLE

        // Check permission


        val shortTabTexts = listOf("Sensors", "Combat Sim", "Weapon", "S. Orient")
        val fullTabTexts =
            listOf("Sensor Data", "Combat Simulation", "Weapon Control", "Soldier Orientation")

        // Connect the tab layout with the view pager
        TabLayoutMediator(binding.tabs, binding.viewPager) { tab, position ->
            tab.text = shortTabTexts[position]
            Log.d("TabLayoutMediator", "Creating tab for position: $position")
            tab.icon = when (position) {
                0 -> ContextCompat.getDrawable(this, R.drawable.sensor_data)
                1 -> ContextCompat.getDrawable(this, R.drawable.combat_simulation)
                2 -> ContextCompat.getDrawable(this, R.drawable.weapon_control)
                3 -> ContextCompat.getDrawable(this, R.drawable.soldier_orientation)
                else -> null
            }
        }.attach()


        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAnchorView(R.id.fab)
                .setAction("Action", null).show()
        }

        // Add a tab selected listener to change tab text on selection
        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                for (i in 0 until binding.tabs.tabCount) {
                    binding.tabs.getTabAt(i)?.text = shortTabTexts[i]
                    Log.d("addOnTabSelectedListener", "Making tab ${i} to short text")
                }

                // Then set the text for the selected tab
                val position = tab?.position ?: 0
                tab?.text = fullTabTexts[position]
                Log.d(
                    "addOnTabSelectedListener",
                    "making ${shortTabTexts[position]} to ${fullTabTexts[position]}"
                )
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val position = tab?.position ?: 0
                tab?.text = shortTabTexts[position]
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // No action needed on reselection
            }

        })

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

}


// This is the adapter for the view pager
class MyPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {


    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        Log.d("createFragment", "Creating fragment for position: $position")

        return when (position) {
            0 -> SensorDataFragment()
            1 -> CombatSimulationFragment()
            2 -> WeaponSystemControlFragment()
            3 -> SoldierOrientationFragment()
            else -> throw IllegalStateException("Invalid position: $position")

        }

    }
}

