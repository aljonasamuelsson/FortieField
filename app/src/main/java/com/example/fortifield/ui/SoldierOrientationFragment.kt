import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fortifield.databinding.FragmentSoldierOrientationBinding
import com.example.fortifield.simulation.OrientationDeterminer
import com.example.fortifield.sensors.GalaxyWatchService

class SoldierOrientationFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var soldierorientationAdapter: SoldierOrientationAdapter
    private lateinit var orientationDeterminer: OrientationDeterminer

    private var orientationsList: MutableList<OrientationDeterminer> = mutableListOf()
    private var _binding: FragmentSoldierOrientationBinding? = null
    private val binding get() = _binding!!

    companion object {
        private const val TAG = "SoldierOrientationFrag"
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

        //startGalaxyWatchService()

        Log.d(TAG, "onViewCreated: GalaxyWatchService started")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /* override fun onSensorDataReceived(orientation: OrientationDeterminer) {
        Log.d(TAG, "Aljona: onSensorDataReceived: Orientation: $orientation")
        orientationsList.add(orientation)
        soldierorientationAdapter.notifyItemInserted(orientationsList.size - 1)
    } */

}



    /* private fun startGalaxyWatchService() {
        val galaxyWatchService = GalaxyWatchService()
        galaxyWatchService.setSensorDataListener(this)

        val intent = context?.let { GalaxyWatchService.getIntent(it) }
        intent?.let { context?.startService(it) }
    } */

