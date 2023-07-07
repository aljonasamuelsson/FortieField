package com.example.fortifield.devices

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.fortifield.sensors.SensorData
import com.example.fortifield.simulation.Orientation
import com.example.fortifield.simulation.OrientationDeterminer
import com.example.fortifield.simulation.Position


class AndroidDeviceHandler(private val context: Context) : SensorEventListener {
    private val sensorManager: SensorManager =
        context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val sensorValueMap: MutableMap<Sensor, SensorData> = mutableMapOf()

    val sensorData = MutableLiveData<List<SensorData>>()


    fun handleDevice() {
        val sensors = sensorManager.getSensorList(Sensor.TYPE_ALL)
        sensors.forEach { sensor ->
            sensorManager.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    fun stopHandlingDevice() {
        Log.d("Aljona_AndroidDeviceHandler", "androidDeviceHandler-> Stopped handling device")

        sensorManager.unregisterListener(this)
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        // Implement what happens when sensor accuracy changes here
    }

    override fun onSensorChanged(event: SensorEvent) {

        val sensor = event.sensor
        val sensorName = "${sensor.name} (${sensor.type})"
        val values = event.values

        if (values.size >= 3) {
            val xValue = values[0]
            val yValue = values[1]
            val zValue = values[2]

            val newSensorData = SensorData(sensor, xValue, yValue, zValue)
            sensorValueMap[sensor] = newSensorData

            val updatedSensorDataList = sensorValueMap.values.toList()
            sensorData.value = updatedSensorDataList
            sensorData.postValue(updatedSensorDataList)

        }

    }

}
