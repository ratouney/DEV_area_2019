package AppFragment

import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.area.R


class ServiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val textViewView: Button

    fun bind(app: MyServices) {
        textViewView.setText(app.description)
    }

    init {
        textViewView = itemView.findViewById(R.id.action) as Button
    }
}