package com.example.fortifield.sensors

import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.example.fortifield.simulation.Orientation
import com.example.fortifield.simulation.OrientationDeterminer
import com.example.fortifield.simulation.Position
import com.samsung.android.sdk.SsdkUnsupportedException
import com.samsung.android.sdk.accessory.SA
import com.samsung.android.sdk.accessory.SAAgent
import com.samsung.android.sdk.accessory.SAAgentV2
import com.samsung.android.sdk.accessory.SAPeerAgent
import com.samsung.android.sdk.accessory.SASocket


 /*interface SensorDataListener {
    fun onSensorDataReceived(orientation: OrientationDeterminer)
} */

class GalaxyWatchService  {
   /* companion object{
        private const val TAG = "GalaxyWatchService"

        fun getIntent(context: Context): Intent {
            return Intent(context, GalaxyWatchService::class.java)
        }
    }
    private var listener: SensorDataListener? = null




    fun setSensorDataListener(listener: SensorDataListener) {
        this.listener = listener
    }

    override fun onBind(intent: Intent): IBinder? {
        // Implement the onBind method
        return null
    }

    override fun onFindPeerAgentsResponse(peerAgents: Array<SAPeerAgent>?, result: Int) {
        Log.d(TAG, "ALJONA: onFindPeerAgentsResponse: Entered function")

        if(result == PEER_AGENT_FOUND){
            Log.d(TAG, "ALJONA: onFindPeerAgentsResponse: Peer agent found")

            peerAgents?.forEach { acceptServiceConnectionRequest(it) }
        }else{
            Log.d(TAG, "ALJONA: onFindPeerAgentsResponse: Peer agent not found")
            //handle other classes
        }
    }

    override fun onServiceConnectionRequested(peerAgent: SAPeerAgent?) {
        Log.d(TAG, "ALJONA: onServiceConnectionRequested")

        // Handle the case where a service connection is requested
        acceptServiceConnectionRequest(peerAgent)
    }

    inner class MySocket(private val listener: SensorDataListener?) : SASocket(TAG) {
        override fun onReceive(channelId: Int, data: ByteArray) {
            val sensorData = String(data)
            Log.d(TAG, "ALJONA: MySocket: onReceive: Received sensor data: $sensorData")


            val orientation = parseSensorData(sensorData)
            listener?.onSensorDataReceived(orientation)
            // Parse the sensor data and update the line chart
        }

        private fun parseSensorData(data: String): OrientationDeterminer {
            // Parse the sensor data and return an OrientationDeterminer instance
            // This parsing will depend on the format of your data String.
            val dataList = data.split(" ")
            val position = Position(dataList[0].toFloat(), dataList[1].toFloat())
            val orientation = Orientation(System.currentTimeMillis() - 10000, dataList[2].toDouble())
            return OrientationDeterminer(position, orientation, dataList[3], dataList[4])
        }

        override fun onError(channelId: Int, errorMessage: String, errorCode: Int) {
            Log.e(TAG, "ALJONA: MySocket: onError: $errorMessage, errorCode: $errorCode")

            // Handle any errors
        }

        override fun onServiceConnectionLost(reason: Int) {
            Log.d(TAG, "ALJONA: MySocket: onServiceConnectionLost: Reason: $reason")

            // Handle the case where the service connection is lost
        }
    } */
}
