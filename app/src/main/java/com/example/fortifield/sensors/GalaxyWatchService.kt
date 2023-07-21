package com.example.fortifield.sensors

import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.fortifield.simulation.Orientation
import com.example.fortifield.simulation.OrientationDeterminer
import com.example.fortifield.simulation.Position


interface SensorDataListener {
    fun onSensorDataReceived(orientation: OrientationDeterminer)
}

class GalaxyWatchService : Service() {
    companion object{
        private const val TAG = "GalaxyWatchService"

        fun getIntent(context: Context): Intent {
            return Intent(context, GalaxyWatchService::class.java)
        }
    }
    private var listener: SensorDataListener? = null

    fun setSensorDataListener(listener: SensorDataListener) {
        this.listener = listener
    }

    override fun onCreate() {
        super.onCreate()

    }

    override fun onBind(intent: Intent): IBinder? {
        // Return the communication channel to the service.
        throw UnsupportedOperationException("Not yet implemented")
    }


}