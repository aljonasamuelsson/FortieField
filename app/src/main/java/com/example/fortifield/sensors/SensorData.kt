package com.example.fortifield.sensors

import android.graphics.Color
import android.hardware.Sensor
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet

data class SensorData(
    val sensor: Sensor,
    val axis: String,
    var value: Float
) {
    fun setupLineChart(lineChart: LineChart) {
        val lineEntries = listOf(Entry(0f, value))

        val lineDataSet = LineDataSet(lineEntries, "Sensor Data")
        lineDataSet.color = Color.BLUE // Customize the line color if needed
        lineDataSet.setDrawCircles(false) // Disable circle markers for data points
        lineDataSet.setDrawValues(false) // Disable value display for data points

        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(lineDataSet)

        val lineData = LineData(dataSets)

        lineChart.data = lineData

        // Adjust the y-axis range for better visibility
        val yAxis = lineChart.axisLeft
        yAxis.axisMinimum = 0f // Set the minimum value for the y-axis
        yAxis.axisMaximum = 10f // Set the maximum value for the y-axis

        // Create a description without axis information
        val description = Description()
        description.text = ""

        // Set the chart description
        lineChart.description = description

        // Create a description for the axis information and position it below the title
        val axisDescription = Description()
        axisDescription.text = "Axis: $axis"
        axisDescription.setPosition(0f, 0f) // Position the description below the title

        // Add the axis description to the chart
        lineChart.extraBottomOffset = 30f // Adjust the distance between the title and the axis description
        lineChart.description = axisDescription

        lineChart.setPinchZoom(true)
        lineChart.isDoubleTapToZoomEnabled = true
        lineChart.setScaleEnabled(true)

        lineChart.notifyDataSetChanged()
        lineChart.invalidate()
    }
}
