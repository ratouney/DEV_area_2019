package AppFragment

import ReactionFragment
import android.app.Dialog
import android.app.PendingIntent.getActivity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.example.area.APICalls
import com.example.area.R


class ServiceAdapter(list: List<MyServices>, context: Context, dialog: Dialog) :
    RecyclerView.Adapter<ServiceViewHolder?>() {
    private lateinit var view: View
    var list: List<MyServices>
    val act: Context = context
    val dialog : Dialog = dialog

    fun onClick() {
        val activity = act as AppCompatActivity
        val myFragment: Fragment = ReactionFragment()
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.frame, myFragment).addToBackStack(null).commit()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, itemType: Int): ServiceViewHolder {
        view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.focus_list_buttons, viewGroup, false)
        return ServiceViewHolder(view)
    }

    override fun onBindViewHolder(myViewHolder: ServiceViewHolder, position: Int) {
        val myObject: MyServices = list[position]
        val txt: Button = view.findViewById(R.id.action)
        txt.setText(myObject.description)
        txt.setOnClickListener {
            if (MyArea.action != null)
                MyArea.reaction = myObject.id
            else
                MyArea.action = myObject.id
            println(MyArea.action)
            println(MyArea.reaction)
            dialog.hide()
            if (MyArea.action != null && MyArea.reaction != null)
                APICalls.POST.LinkArea(MyArea.action, MyArea.reaction, kotlin.random.Random.nextInt().toString())
            onClick()
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