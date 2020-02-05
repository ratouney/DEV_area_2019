package HomeActivity

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.area.R


class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val textViewView: TextView
    private val imageView: ImageView

    fun bind(app: MyApp) {
        textViewView.setText(app.text)
        imageView.setImageResource(app.image)
    }

    init {
        textViewView = itemView.findViewById(R.id.text) as TextView
        imageView = itemView.findViewById(R.id.image) as ImageView
    }
}