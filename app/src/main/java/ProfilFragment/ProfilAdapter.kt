package ProfilFragment

import MyLinks
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.area.APICalls
import com.example.area.R


class ProfilAdapter(list: MutableList<MyLinks>, context: Context) : RecyclerView.Adapter<ProfilViewHolder?>() {
    private lateinit var view: View
    var list: MutableList<MyLinks>
    val act: Context = context

    fun remove(position: Int) {
        list.removeAt(position)
        notifyItemChanged(position)
        notifyItemRangeRemoved(position, 1)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, itemType: Int): ProfilViewHolder {
        view = LayoutInflater.from(viewGroup.context).inflate(R.layout.area_card, viewGroup, false)
        return ProfilViewHolder(view)
    }

    override fun onBindViewHolder(myViewHolder: ProfilViewHolder, position: Int) {
        val myObject: MyLinks = list[position]
        val delete = view.findViewById<ImageView>(R.id.close)
        delete.setOnClickListener {
            remove(position)
        }
        myViewHolder.bind(myObject)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    init {
        this.list = list
    }

}