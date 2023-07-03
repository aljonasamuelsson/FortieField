package com.example.fortifield.ui

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fortifield.R
import com.example.fortifield.sensors.SensorData
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class SensorDataAdapter(private val sensorData: List<SensorData>) :
    RecyclerView.Adapter<SensorDataAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val sensorName: TextView = view.findViewById(R.id.sensor_name)
        val sensorValue: TextView = view.findViewById(R.id.sensor_value)
        val sensorGraph: LineChart = view.findViewById(R.id.sensor_graph)
        // TODO: Add a GraphView for the sensor graph
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.sensor_data_item, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sensorDataItem = sensorData[position]
        holder.sensorName.text = sensorDataItem.sensor.name
        holder.sensorValue.text = sensorDataItem.value.toString()

        val lineEntries = sensorData.mapIndexed { index, data ->
            Entry(index.toFloat(), data.value.toFloat())
        }

        val lineDataSet = LineDataSet(lineEntries, "Sensor Data")
        lineDataSet.color = Color.BLUE
        lineDataSet.setDrawCircles(false)
        lineDataSet.setDrawValues(false)

        val lineData = LineData(lineDataSet)
        holder.sensorGraph.data = lineData
        holder.sensorGraph.invalidate()

    }

    override fun getItemCount() = sensorData.size
}
