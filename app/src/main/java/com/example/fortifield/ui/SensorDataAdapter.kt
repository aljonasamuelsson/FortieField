package com.example.fortifield.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fortifield.R
import com.example.fortifield.sensors.SensorData
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.ColorTemplate

class SensorDataAdapter : RecyclerView.Adapter<SensorDataAdapter.ViewHolder>() {

    private var sensorDataList: List<SensorData> = listOf()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val sensorName: TextView = view.findViewById(R.id.sensor_name)
        val sensorValues: TextView = view.findViewById(R.id.sensor_values)
        val sensorChart: LineChart = view.findViewById(R.id.sensor_graph)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.sensor_data_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val sensorValue = sensorDataList[position]
        holder.sensorName.text = sensorValue.sensor.name

        val lineEntries = mutableListOf<Entry>()
        val xValue = sensorValue.value
        val yValue = sensorValue.value
        val zValue = sensorValue.value

        lineEntries.add(Entry(0f, xValue))
        lineEntries.add(Entry(1f, yValue))
        lineEntries.add(Entry(2f, zValue))

        val sensorValuesText = "X: $xValue\nY: $yValue\nZ: $zValue"
        holder.sensorValues.text = sensorValuesText

        val lineDataSet = LineDataSet(lineEntries, "Sensor Data")
        lineDataSet.color = ColorTemplate.MATERIAL_COLORS[position % ColorTemplate.MATERIAL_COLORS.size]

        val dataSets = ArrayList<ILineDataSet>()
        dataSets.add(lineDataSet)

        val lineData = LineData(dataSets)
        holder.sensorChart.data = lineData

        // Set chart description
        val description = Description()
        description.text = "Sensor Data"
        holder.sensorChart.description = description



        holder.sensorChart.setPinchZoom(true)
        holder.sensorChart.isDoubleTapToZoomEnabled = true
        holder.sensorChart.setScaleEnabled(true)

        holder.sensorChart.notifyDataSetChanged()
        holder.sensorChart.invalidate()
    }

    override fun getItemCount() = sensorDataList.size

    fun updateData(newData: List<SensorData>) {
        sensorDataList = newData
        notifyDataSetChanged()
    }
}

