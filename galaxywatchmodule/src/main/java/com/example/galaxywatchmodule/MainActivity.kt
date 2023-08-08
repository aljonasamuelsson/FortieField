package com.example.galaxywatchmodule

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.galaxywatchmodule.ui.theme.FortiFieldTheme

class SensorViewModel : ViewModel() {
    val sensorData = mutableStateOf("No data")
}

class MainActivity : ComponentActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private var accelerometer: Sensor? = null
    private var gyroscope: Sensor? = null
    private lateinit var viewModel: SensorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("Aljona", "GalaxyWatchModule - onCreate")

        // Initialize the sensor manager and sensors
        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        gyroscope = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        // Initialize the ViewModel
        viewModel = ViewModelProvider(this).get(SensorViewModel::class.java)

        setContent {
            SensorScreen(viewModel)
        }
    }

    @Composable
    fun SensorScreen(viewModel: SensorViewModel) {
        FortiFieldTheme {
            // A surface container using the 'background' color from the theme
            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                // Display the sensor data on the screen
                Text(text = viewModel.sensorData.value)
            }
        }
    }

    override fun onResume() {
        super.onResume()

        Log.d("Aljona", "GalaxyWatchModule - onResume")

        // Register the sensor listeners
        accelerometer?.also { accelerometer ->
            sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        }
        gyroscope?.also { gyroscope ->
            sensorManager.registerListener(this, gyroscope, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onPause() {
        super.onPause()

        Log.d("Aljona", "GalaxyWatchModule - onPause")

        // Unregister the sensor listeners
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        Log.d("Aljona", "GalaxyWatchModule - onSensorChanged")

        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            // TODO: Handle accelerometer data
            viewModel.sensorData.value = "Accelerometer data: ${event.values.joinToString()}"
        } else if (event.sensor.type == Sensor.TYPE_GYROSCOPE) {
            // TODO: Handle gyroscope data
            viewModel.sensorData.value = "Gyroscope data: ${event.values.joinToString()}"
        }
    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        Log.d("Aljona", "GalaxyWatchModule - onAccuracyChanged")
    }
}
