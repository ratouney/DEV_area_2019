package AppFragment

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.area.R


class ButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val textViewView: TextView

    fun bind(app: MyActions) {
        textViewView.setText(app.text)
    }

    init {
        textViewView = itemView.findViewById(R.id.action) as TextView
    }
}