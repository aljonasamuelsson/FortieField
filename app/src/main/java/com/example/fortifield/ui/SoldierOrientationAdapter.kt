import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fortifield.R
import com.example.fortifield.simulation.OrientationDeterminer
import java.text.SimpleDateFormat
import java.util.Date

class SoldierOrientationAdapter(private var orientationDeterminerList: MutableList<OrientationDeterminer>) :
    RecyclerView.Adapter<SoldierOrientationAdapter.MovementViewHolder>() {

    class MovementViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val directionTextView: TextView = view.findViewById(R.id.direction)
        val weaponPositionTextView: TextView = view.findViewById(R.id.weapon_position)
        val timestampTextView: TextView = view.findViewById(R.id.timestamp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovementViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movement_item, parent, false)
        return MovementViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovementViewHolder, position: Int) {
        val orientationDeterminer = orientationDeterminerList[position]
        holder.directionTextView.text = orientationDeterminer.direction
        holder.weaponPositionTextView.text = orientationDeterminer.weaponPosition
        holder.timestampTextView.text = formatTimestamp(orientationDeterminer.orientation.timestamp)
    }

    override fun getItemCount() = orientationDeterminerList.size

    private fun formatTimestamp(timestamp: Long): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return dateFormat.format(Date(timestamp))
    }

    fun updateData(newData: List<OrientationDeterminer>) {
        orientationDeterminerList.clear()
        orientationDeterminerList.addAll(newData)
        notifyDataSetChanged()
    }
}
