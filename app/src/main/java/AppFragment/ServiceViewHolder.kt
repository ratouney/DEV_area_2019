package AppFragment

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.area.R


class ServiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val textViewView: Button
    private val argViewView: TextView

    fun bind(app: MyServices) {
        textViewView.setText(app.description)
        argViewView.text = ""
    }

    init {
        textViewView = itemView.findViewById(R.id.action) as Button
        argViewView = itemView.findViewById(R.id.argument) as TextView
    }
}