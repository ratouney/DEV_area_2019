package homeactivity


import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.area.R


class MyAdapter(list: List<MyApp>, context: Context) : RecyclerView.Adapter<MyViewHolder?>() {
    private lateinit var view: View
    var list: List<MyApp>
    val act: Context = context

    override fun onCreateViewHolder(viewGroup: ViewGroup, itemType: Int): MyViewHolder {
        view = LayoutInflater.from(viewGroup.context).inflate(R.layout.cell_cards, viewGroup, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, position: Int) {
        val myObject: MyApp = list[position]
        val card: CardView = view.findViewById(R.id.card)
        val dialog = Dialog(act)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setContentView(R.layout.focus_app)
        card.setOnClickListener {
            val img: ImageView = dialog.findViewById(R.id.focus_img)
            val txt: TextView = dialog.findViewById(R.id.focus_txt)
            txt.setText(myObject.text)
            txt.setTextColor(Color.parseColor("#FFFFFF"))
            img.setImageResource(myObject.image)
            dialog.show()
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