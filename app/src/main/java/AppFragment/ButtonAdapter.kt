package AppFragment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.area.R


class ButtonAdapter(list: List<MyActions>, context: Context) : RecyclerView.Adapter<ButtonViewHolder?>() {
    private lateinit var view: View
    var list: List<MyActions>

    override fun onCreateViewHolder(viewGroup: ViewGroup, itemType: Int): ButtonViewHolder {
        view = LayoutInflater.from(viewGroup.context).inflate(R.layout.focus_list_buttons, viewGroup, false)
        return ButtonViewHolder(view)
    }

    override fun onBindViewHolder(myViewHolder: ButtonViewHolder, position: Int) {
        val myObject: MyActions = list[position]
        myViewHolder.bind(myObject)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    init {
        this.list = list
    }
}