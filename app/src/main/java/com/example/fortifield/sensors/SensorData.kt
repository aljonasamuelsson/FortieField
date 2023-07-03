package com.example.fortifield.sensors

import android.graphics.Color
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

data class SensorData(
    val sensor: Sensor, val value: Double
) {
    companion object {
        fun createMockData(): List<SensorData> {
            return listOf(
                SensorData(Sensor("Accelerometer", "Motion"), 9.81),
                SensorData(Sensor("Gyroscope", "Motion"), 1.0),
                SensorData(Sensor("Magnetometer", "Environment"), 25.0),
                SensorData(
                    Sensor("GPS", "Position"), 57.7089
                ),
                SensorData(
                    Sensor("GPS", "Position"), 57.7089
                ),
                SensorData(
                    Sensor("GPS", "Position"), 57.7089
                )
            )
        }
    }

    fun setupLineChart(lineChart: LineChart) {
        val lineEntries = listOf(Entry(0f, value.toFloat()))
        val lineDataSet = LineDataSet(lineEntries, "Sensor Data")
        lineDataSet.color = Color.BLUE // Customize the line color if needed
        lineDataSet.setDrawCircles(false) // Disable drawing circles for data points
        lineDataSet.setDrawValues(false) // Disable displaying values on data points

        val lineData = LineData(lineDataSet)

        lineChart.data = lineData

        // Customize the y-axis range for better visibility
        val yAxis = lineChart.axisLeft
        yAxis.axisMinimum = 0f // Set the minimum value for the y-axis
        yAxis.axisMaximum = 10f // Set the maximum value for the y-axis

        lineChart.invalidate()
    }
}
