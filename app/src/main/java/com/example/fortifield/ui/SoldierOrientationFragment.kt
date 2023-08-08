import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fortifield.databinding.FragmentSoldierOrientationBinding
import com.example.fortifield.sensors.SensorData
import com.example.fortifield.simulation.OrientationDeterminer
import com.example.fortifield.sensors.WatchSensorDataReceiver
import com.example.fortifield.simulation.Orientation
import com.example.fortifield.simulation.Position

class SoldierOrientationFragment : Fragment() {
    private lateinit var soldierorientationAdapter: SoldierOrientationAdapter
    private lateinit var orientationDeterminer: OrientationDeterminer
    private lateinit var sensorDataReceiver: WatchSensorDataReceiver

    private var orientationsList: MutableList<OrientationDeterminer> = mutableListOf()
    private var _binding: FragmentSoldierOrientationBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val TAG = "SoldierOrientationFrag"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sensorDataReceiver = WatchSensorDataReceiver(requireContext())
        sensorDataReceiver.sensorData.observe(this, Observer { sensorData ->
            // TODO: Convert the sensorData into an OrientationDeterminer object
            val orientation = convertSensorDataToOrientation(sensorData)

            orientationsList.add(orientation)
            soldierorientationAdapter.notifyItemInserted(orientationsList.size - 1)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSoldierOrientationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        soldierorientationAdapter = SoldierOrientationAdapter(orientationsList)
        binding.soldierOrientationRecyclerView.adapter = soldierorientationAdapter
        binding.soldierOrientationRecyclerView.layoutManager = LinearLayoutManager(context)

        Log.d(TAG, "onViewCreated: GalaxyWatchService started")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun convertSensorDataToOrientation(sensorData: SensorData): OrientationDeterminer {
        // Extract the necessary values from the sensor data
        val xValue = sensorData.xValue
        val yValue = sensorData.yValue
        val zValue = sensorData.zValue

        // Use these values to determine the position, orientation, direction, and weaponPosition
        val position = Position(xValue, yValue) // Assuming that xValue and yValue can be used as position coordinates
        val orientation = Orientation(System.currentTimeMillis(), zValue.toDouble()) // Assuming that zValue can be used as the orientation angle

        val direction = if (xValue > 0) "RIGHT" else "LEFT" // This is a very simplistic way to determine the direction. You'll need to replace this with your actual logic.
        val weaponPosition = if (yValue > 0) "UP" else "DOWN" // This is a very simplistic way to determine the weapon position. You'll need to replace this with your actual logic.

        return OrientationDeterminer(position, orientation, direction, weaponPosition)
    }



}
