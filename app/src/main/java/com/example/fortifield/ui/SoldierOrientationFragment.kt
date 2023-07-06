import SoldierOrientationAdapter
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fortifield.R
import com.example.fortifield.databinding.FragmentSoldierOrientationBinding
import com.example.fortifield.devices.AndroidDeviceHandler
import com.example.fortifield.simulation.OrientationDeterminer

class SoldierOrientationFragment : Fragment() {
    private var _binding: FragmentSoldierOrientationBinding? = null
    private val binding get() = _binding!!

    private lateinit var soldierOrientationAdapter: SoldierOrientationAdapter
    private lateinit var androidDeviceHandler: AndroidDeviceHandler

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("SoldierOrientationFragment", "SoldierOri-view is being created")
        _binding = FragmentSoldierOrientationBinding.inflate(inflater, container, false)

        soldierOrientationAdapter = SoldierOrientationAdapter(mutableListOf())
        binding.soldierOrientationRecyclerView.adapter = soldierOrientationAdapter
        binding.soldierOrientationRecyclerView.layoutManager = LinearLayoutManager(context)

        androidDeviceHandler = AndroidDeviceHandler(requireContext())
        androidDeviceHandler.handleDevice()

        androidDeviceHandler.soldierOrientation.observe(viewLifecycleOwner, Observer { orientationDeterminer ->
            // Update the soldier's orientation
            soldierOrientationAdapter.updateData(listOf(orientationDeterminer))
        })

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d("SoldierOrientationFragment", "SoldierOri-view has been destroyed")
    }
}
