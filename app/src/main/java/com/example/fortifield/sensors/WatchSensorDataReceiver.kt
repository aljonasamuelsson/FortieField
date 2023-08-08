package com.example.fortifield.sensors

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.wearable.DataClient
import com.google.android.gms.wearable.DataEvent
import com.google.android.gms.wearable.DataMap
import com.google.android.gms.wearable.DataMapItem
import com.google.android.gms.wearable.Wearable

class WatchSensorDataReceiver(context: Context) {

    private val dataClient: DataClient = Wearable.getDataClient(context)

    private val _sensorData = MutableLiveData<SensorData>()
    val sensorData: LiveData<SensorData> = _sensorData

    init {
        dataClient.addListener { dataEvents ->
            for (event in dataEvents) {
                val dataByteArray = event.dataItem.data
                if (dataByteArray != null) {
                    val dataMapItem = DataMapItem.fromDataItem(event.dataItem)
                    val dataMap = dataMapItem.dataMap
                    val accelerometerData = dataMap.getFloatArray("accelerometer")
                    val gyroscopeData = dataMap.getFloatArray("gyroscope")

                    Log.d("Aljona", "Received sensor data from watch")

                    // TODO: Convert the raw sensor data into a SensorData object and post it to _sensorData
                }
            }
        }
    }
}
