import SoldierOrientationAdapter
import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fortifield.R
import com.example.fortifield.databinding.FragmentSoldierOrientationBinding
import com.example.fortifield.devices.AndroidDeviceHandler
import com.example.fortifield.simulation.OrientationDeterminer

class SoldierOrientationFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var soldierorientationAdapter: SoldierOrientationAdapter
    private lateinit var orientationDeterminer: OrientationDeterminer

    private val orientationsList: List<OrientationDeterminer> = OrientationDeterminer.getMockOrientations()
    private var _binding: FragmentSoldierOrientationBinding? = null
    private val binding get() = _binding!!


    companion object{
        private const val MY_PERMISSIONS_REQUEST_BODY_SENSORS = 0
        private const val MY_PERMISSIONS_REQUEST_ACTIVITY_RECOGNITION = 1
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("SoldierOrientationFragment", "SoldierOri-view is being created")
        _binding = FragmentSoldierOrientationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("SoldierOrientationFragment", "SoldierOri-view has been created")


        soldierorientationAdapter = SoldierOrientationAdapter(orientationsList as MutableList<OrientationDeterminer>)
        binding.soldierOrientationRecyclerView.adapter = soldierorientationAdapter
        binding.soldierOrientationRecyclerView.layoutManager = LinearLayoutManager(context)

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d("SoldierOrientationFragment", "SoldierOri-view has been destroyed")
    }
}
